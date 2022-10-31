package com.itany.controller;

import com.itany.client.UserClient;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.exception.AppException;
import com.itany.input.UserInput;
import com.itany.validation.Insert;
import com.itany.vo.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController.
 *
 * @author Thou
 * @date 2022/10/31
 */
@RestController
@RequestMapping("/web")
public class UserController {

    @Autowired
    private UserClient userClient;

    @PostMapping("/user/register")
    public ResponseResult<Object> userRegister(UserInput input) {
        if (StringUtils.isBlank(input.getName())) {
            throw new AppException(AppExceptionMsgEnum.USER_NAME_IS_BLACK);
        }

        if (StringUtils.isBlank(input.getPhone())) {
            throw new AppException(AppExceptionMsgEnum.USER_PHONE_IS_BLACK);
        }

        if (StringUtils.isBlank(input.getPassword())) {
            throw new AppException(AppExceptionMsgEnum.USER_PASSWORD_IS_BLACK);
        }

        if (StringUtils.isBlank(input.getRePassword())) {
            throw new AppException(AppExceptionMsgEnum.USER_REPASSWORD_IS_BLACK);
        }

        if (!StringUtils.equals(input.getPassword(), input.getRePassword())) {
            throw new AppException(AppExceptionMsgEnum.USER_PASSWORD_NOT_EQUALS);
        }

        if (StringUtils.isBlank(input.getCheckCode())) {
            throw new AppException(AppExceptionMsgEnum.USER_CHECKCODE_IS_BLACK);
        }

        return userClient.userRegister(input);
    }
}
