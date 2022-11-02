package com.itany.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.itany.conf.AlipayConfig;
import com.itany.constants.AppConsts;
import com.itany.dao.LifeDao;
import com.itany.dto.MemberCompanyDTO;
import com.itany.service.PaymentService;
import com.itany.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

/**
 * PaymentServiceImpl.
 *
 * @author Thou
 * @date 2022/11/1
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private LifeDao lifeDao;

    @Override
    public String getQrCode(Integer level, Integer companyid, Integer userid) {
        // 声明一个返回字符串
        String qrCode = "";
        // 生成订单号
        String no = System.currentTimeMillis() + "";
        // 声明扫码支付的请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        // 声明参数对象
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        // 订单号
        model.setOutTradeNo(no);

        // 价格
        String totalAmount = null;
        for (Integer vipNo : AppConsts.VIP_NO) {
            if (vipNo.equals(level)) {
                totalAmount = AppConsts.VIP_PRICE[level];
                break;
            }
        }
        if (null != totalAmount) {
            model.setTotalAmount(totalAmount);
        }  else {
            qrCode = "VIP等级不合法";
            return qrCode;
        }

        // 订单主题
        model.setSubject("VIP购买");

        // 异步回调地址
        request.setNotifyUrl(AlipayConfig.NOTIFY_URL + userid);
        request.setBizModel(model);
        // 执行预下单接口
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (null != response) {
            if (response.isSuccess()) {
                qrCode = response.getQrCode();

                // 查询最近购买记录
                MemberCompanyDTO latest = lifeDao.getLatest();

                // 生成订单数据持久化到数据库
                MemberCompanyDTO memberCompanyDTO = new MemberCompanyDTO();
                memberCompanyDTO.setCommpanyid(companyid);
                memberCompanyDTO.setNo(no);
                memberCompanyDTO.setMemberid(level);
                memberCompanyDTO.setFlag(AppConsts.ORDER_UNPAID);
                if (null == latest) {
                    memberCompanyDTO.setStartdate(DateUtils.localDateToDate(LocalDate.now()));
                    memberCompanyDTO.setEnddate(DateUtils.localDateToDate(LocalDate.now().plusDays(AppConsts.VIP_DAY[level])));
                } else {
                    Date end = latest.getEnddate();
                    memberCompanyDTO.setStartdate(end);
                    memberCompanyDTO.setEnddate(DateUtils.dateAddDay(end, AppConsts.VIP_DAY[level]));
                }

                log.warn("[PaymentServiceImpl] 生成订单 ==> " + memberCompanyDTO.toString());
                lifeDao.insertMemberCompany(memberCompanyDTO);
            } else {
                qrCode = "请刷新页面重新支付";
            }
        } else {
            qrCode = "请刷新页面重新支付";
        }

        return qrCode;
    }

    @Override
    public void finishOrder(String no) {
        MemberCompanyDTO memberCompany = lifeDao.getMemberCompanyByNo(no);
        if (null != memberCompany) {
            lifeDao.updateMemberCompanyFlag(no, AppConsts.ORDER_PAID);
            log.warn("[finishOrder] 完成支付，修改订单状态");
        }
    }
}
