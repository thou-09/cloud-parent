package com.itany.controller;

import com.alibaba.fastjson.JSON;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.UserDTO;
import com.itany.exception.AppException;
import com.itany.input.SsoInput;
import com.itany.input.UserInput;
import com.itany.service.SsoService;
import com.itany.util.SsoTicketUtils;
import com.itany.vo.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * SsoController.
 *
 * @author Thou
 * @date 2022/10/31
 */
@RestController
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private SsoService ssoService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public ResponseResult<Object> login(SsoInput input, HttpServletResponse response, HttpSession session) {
        if (StringUtils.isBlank(input.getName())) {
            throw new AppException(AppExceptionMsgEnum.USER_NAME_IS_BLACK);
        }
        if (StringUtils.isBlank(input.getPassword())) {
            throw new AppException(AppExceptionMsgEnum.USER_PASSWORD_IS_BLACK);
        }
        if (StringUtils.isBlank(input.getCheckCode())) {
            throw new AppException(AppExceptionMsgEnum.USER_CHECKCODE_IS_BLACK);
        }

        UserDTO user = ssoService.loginUser(input);
        // 生成 token
        String token = UUID.randomUUID().toString();
        // 存入 redis
        redisTemplate.opsForValue().set("USER_TOKEN::" + token, JSON.toJSONString(user), 1800, TimeUnit.SECONDS);
        // 存入 cookie
        Cookie ck = new Cookie("TT_TOKEN", token);
        ck.setPath("/");
        response.addCookie(ck);
        // 存入 session
        session.setAttribute("loginUser", user);

        String url = null;
        boolean legal = SsoTicketUtils.isLegal(input.getTicket());
        SsoTicketUtils.destroy(input.getTicket());
        if (legal) {
            url = input.getServer();
        } else {
            url = "http://localhost:9001/index";
        }

        return ResponseResult.success(url);
    }
}
