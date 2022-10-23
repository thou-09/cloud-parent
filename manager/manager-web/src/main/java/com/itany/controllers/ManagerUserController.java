package com.itany.controllers;

import com.itany.dto.ManagerUserDTO;
import com.itany.input.ManagerUserInput;
import com.itany.service.ManagerUserService;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ManagerUserController.
 *
 * @author Thou
 * @date 2022/10/18
 */
@RestController
@RequestMapping("/managerUsers")
public class ManagerUserController {

    @Autowired
    private ManagerUserService managerUserService;

    @PostMapping("/login")
    public ResponseResult<Object> findByParams(@Validated ManagerUserInput in) {
        ManagerUserDTO vo = managerUserService.login(in);
        // TODO 缓存到 session 中
        return ResponseResult.success();
    }
}
