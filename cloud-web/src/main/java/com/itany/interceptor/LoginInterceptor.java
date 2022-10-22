package com.itany.interceptor;

import com.itany.ApplicationHold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        boolean flag=false;
        StringRedisTemplate template = ApplicationHold.getbean(StringRedisTemplate.class);
        Cookie[] cks =  request.getCookies();
        if(cks!=null){
            for(int i=0;i<cks.length;i++){
                   Cookie ck =cks[i];
                   if("TT_TOKEN".equals(ck.getName())){
                      String token = ck.getValue();
                      String obj = template.opsForValue().get("USER_TOKEN::"+token);
                       System.out.println(obj + "---------------");
                       flag=true;
                   }
            }
        }
        if(!flag){
            StringBuffer httpUrl = request.getRequestURL();
            String url = "http://localhost:9001/showlogin?server="
                    + URLEncoder.encode(httpUrl.toString(),"utf-8");
            response.sendRedirect(url);
        }
        return flag;
    }
}
