package com.itany.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class OtherConfig {

    // 显示声明CommonsMultipartResolver为mutipartResolver

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver()
    {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(40960);
        // 上传文件大小 5M 5*1024*1024
        resolver.setMaxUploadSize(10 * 1024 * 1024);
        return resolver;
    }
}
