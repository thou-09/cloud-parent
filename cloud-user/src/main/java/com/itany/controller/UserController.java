package com.itany.controller;

import com.itany.input.UserInput;
import com.itany.service.UserService;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController.
 *
 * @author Thou
 * @date 2022/10/31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult<Object> userRegister(@RequestBody UserInput input) {
        userService.registerUser(input);
        return ResponseResult.success();
    }
}
