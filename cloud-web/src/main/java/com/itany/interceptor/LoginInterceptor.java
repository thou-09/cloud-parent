package com.itany.interceptor;

import com.alibaba.fastjson.JSON;
import com.itany.ApplicationHold;
import com.itany.dto.UserDTO;
import com.itany.util.SsoTicketUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        StringRedisTemplate template = ApplicationHold.getBean(StringRedisTemplate.class);
        Cookie[] cks =  request.getCookies();
        if(cks != null){
            for (Cookie ck : cks) {
                if ("TT_TOKEN".equals(ck.getName())) {
                    String token = ck.getValue();
                    String obj = template.opsForValue().get("USER_TOKEN::" + token);
                    if (null == obj || "".equals(obj)) {
                        flag = false;
                    } else {
                        UserDTO user = JSON.parseObject(obj, UserDTO.class);
                        UserDTO loginUser = (UserDTO)request.getSession().getAttribute("loginUser");
                        if (null == loginUser) {
                            flag = false;
                        } else {
                            if (!user.getId().equals(loginUser.getId())) {
                                flag = false;
                            } else {
                                flag = true;
                            }
                        }
                    }
                }
            }
        }

        if(!flag){
            StringBuffer httpUrl = request.getRequestURL();
            String url = "http://localhost:9001/action?server="
                    + URLEncoder.encode(httpUrl.toString(),"utf-8")
                    + "&ticket=" + SsoTicketUtils.generateOnceTicket() + "#login";
            response.sendRedirect(url);
        }
        return flag;
    }
}
