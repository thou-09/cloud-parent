package com.itany.controllers;

import com.itany.dto.UserDTO;
import com.itany.input.UserInput;
import com.itany.service.UserService;
import com.itany.validation.Update;
import com.itany.validation.UpdateFlag;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * UserController.
 *
 * @author Thou
 * @date 2022/10/19
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userlist")
    public Map<String, Object> listUsers(UserInput in) {
        return userService.listAllByParams(in);
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyUser(UserInput in) {
        userService.updateUserById(in);
        return ResponseResult.success();
    }

    @PostMapping("/modify/flag")
    public ResponseResult<Object> modifyUserFlag(@Validated({UpdateFlag.class}) UserInput in) {
        userService.updateUserById(in);
        return ResponseResult.success();
    }

    @PostMapping("/user/{id}")
    public ResponseResult<UserDTO> findUser(@PathVariable Integer id) {
        return ResponseResult.success(userService.getUserById(id));
    }
}
