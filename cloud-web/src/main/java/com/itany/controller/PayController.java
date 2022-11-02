package com.itany.controller;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itany.client.LifeClient;
import com.itany.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * PayController.
 *
 * @author Thou
 * @date 2022/11/2
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private LifeClient lifeClient;

    @RequestMapping("/qrCode/{level}")
    public void getQrCode(@PathVariable Integer level, HttpServletRequest request, HttpServletResponse response) {
        UserDTO loginUser = (UserDTO)request.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>(8);
        map.put("loginUser", JSON.toJSONString(loginUser));
        String qrCode = lifeClient.getQrCode(level, map);

        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成二维码
        Map<EncodeHintType,Object> params = new HashMap<>(4);
        params.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(qrCode, BarcodeFormat.QR_CODE, 400, 400, params);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpeg", response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/callback/{companyid}")
    public String payback(@PathVariable Integer companyid, HttpServletRequest request) {
        Map<String, String> param = req2Map(request);
        return lifeClient.payback(companyid, param);
    }

    /**
     * 回调参数解析方法
     *
     * @param request -
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author Thou
     * @date 2022/11/1
     */
    public static Map<String, String> req2Map(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(16);
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (value.length == 1) {
                map.put(key, value[0]);
            } else if (value.length > 1) {
                StringBuilder builder = new StringBuilder();
                for (String s : value) {
                    builder.append(",").append(s);
                }
                map.put(key, builder.toString().substring(1));
            } else {
                map.put(key, "");
            }
        }
        return map;
    }
}
