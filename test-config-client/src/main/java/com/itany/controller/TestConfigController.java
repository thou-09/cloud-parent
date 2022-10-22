package com.itany.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持动态参数刷新
public class TestConfigController {

    @Value("${config.info}")
    private String str;

   @RequestMapping("/config/info")
   public String getInfo(){
       return str;
   }

}
