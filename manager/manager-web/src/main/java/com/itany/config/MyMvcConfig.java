package com.itany.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.exception.AppException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MyMvcConfig.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1.定义一个convert转换消息对象--字符串解析器
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        // 2.将convert添加到converters中并设置优先级
        converters.add(0,converter);
        // 1.定义一个convert转换消息对象--json解析器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.1添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
        // 2.2处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将convert添加到converters中并设置优先级
        converters.add(1,fastConverter);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/commerce_typelist").setViewName("commerce_typelist");
        registry.addViewController("/commpany_examinelist").setViewName("commpany_examinelist");
        registry.addViewController("/commpanymap").setViewName("commpanymap");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/life_typelist").setViewName("life_typelist");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/manager").setViewName("manager");
        registry.addViewController("/messagelist").setViewName("messagelist");
        registry.addViewController("/notice").setViewName("notice");
        registry.addViewController("/permission").setViewName("permission");
        registry.addViewController("/role").setViewName("role");
        registry.addViewController("/server_commerce").setViewName("server_commerce");
        registry.addViewController("/server_examinelist").setViewName("server_examinelist");
        registry.addViewController("/server_life").setViewName("server_life");
        registry.addViewController("/serverlist").setViewName("serverlist");
        registry.addViewController("/subaccount").setViewName("subaccount");
        registry.addViewController("/userlist").setViewName("userlist");
    }
}
