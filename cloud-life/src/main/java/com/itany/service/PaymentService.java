package com.itany.service;

/**
 * PaymentService.
 *
 * @author Thou
 * @date 2022/11/1
 */
public interface PaymentService {

    /**
     * 获取支付二维码
     *
     * @param level -
     * @param companyid -
     * @param userid -
     * @return java.lang.String
     * @author Thou
     * @date 2022/11/1
     */
    String getQrCode(Integer level, Integer companyid, Integer userid);

    /**
     * 完成支付
     *
     * @param no -
     * @author Thou
     * @date 2022/11/2
     */
    void finishOrder(String no);
}
