package com.itany.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * LifeClient
 *
 * @author Thou
 * @date 2022/11/1
 */
@Component
@FeignClient(value = "cloud-life")
public interface LifeClient {

    /**
     * 获得支付二维码
     *
     * @param level -
     * @param map -
     * @return java.lang.String
     * @author Thou
     * @date 2022/11/1
     */
    @RequestMapping("/pay/getQrCode")
    String getQrCode(@RequestParam(value = "level") Integer level, @RequestParam Map<String, Object> map);

    /**
     * 支付回调
     *
     * @param userid -
     * @param param -
     * @return java.lang.String
     * @author Thou
     * @date 2022/11/2
     */
    @RequestMapping("/pay/callback/{userid}")
    String payback(@PathVariable(value = "userid") Integer userid, @RequestParam Map<String, String> param);
}
