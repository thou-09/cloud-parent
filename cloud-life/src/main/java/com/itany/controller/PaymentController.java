package com.itany.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.itany.conf.AlipayConfig;
import com.itany.conf.RabbitMqConfig;
import com.itany.dto.UserDTO;
import com.itany.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * PaymentController.
 *
 * @author Thou
 * @date 2022/11/1
 */
@Slf4j
@RestController
@RequestMapping("/pay")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/getQrCode")
    public String getQrCode(@RequestParam(value = "level") Integer level, @RequestParam Map<String, Object> map) {
        String qrCode = "请登陆后刷新页面重新支付";

        String jsonUser = (String)map.get("loginUser");
        UserDTO loginUser = JSON.parseObject(jsonUser, UserDTO.class);
        if (null == loginUser) {
            return qrCode;
        }
        if (null == loginUser.getCompanyid()) {
            return "您没有绑定服务商，请绑定后再购买 VIP";
        }
        qrCode = paymentService.getQrCode(level, loginUser.getCompanyid(), loginUser.getId());
        return qrCode;
    }

    @RequestMapping("/callback/{userid}")
    public String payback(@PathVariable Integer userid, @RequestParam Map<String, String> param) {
        try {
            boolean flag = AlipaySignature.rsaCheckV1(param, AlipayConfig.PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
            // 将回调消息以 JSON 格式存入 MQ 中
            HashMap<String, Object> m = new HashMap<>(8);
            if (flag) {
                m.put("userid", userid);
                m.put("out_trade_no", param.get("out_trade_no"));
                m.put("trade_status", param.get("trade_status"));
                log.warn("[payback] 支付回调 ==> " + JSON.toJSONString(m));
                if ("TRADE_SUCCESS".equals(param.get("trade_status"))) {
                    rabbitTemplate.convertAndSend(RabbitMqConfig.PAY_EXCHANGE, RabbitMqConfig.PAY_ROUTING_KEY, JSON.toJSONString(m));
                }
            } else {
                log.warn("不是阿里发送的消息");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
