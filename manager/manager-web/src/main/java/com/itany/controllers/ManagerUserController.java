package com.itany.controllers;

import com.alibaba.fastjson.JSON;
import com.itany.config.AuthorityCacheManager;
import com.itany.dto.ManagerUserDTO;
import com.itany.dto.PermissionDTO;
import com.itany.entity.Role;
import com.itany.input.ManagerUserInput;
import com.itany.service.ManagerUserService;
import com.itany.validation.Insert;
import com.itany.validation.Update;
import com.itany.vo.MenuVO;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseResult<Object> findByParams(@Validated ManagerUserInput in, HttpSession session) {
        ManagerUserDTO dto = managerUserService.login(in);
        session.setAttribute("loginUser", dto);
        List<Integer> rids = dto.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        AuthorityCacheManager.USER_ROLE_MAP.put(dto.getId(), rids);
        return ResponseResult.success();
    }

    @RequestMapping("/list")
    public Map<String, Object> listManagerUser(ManagerUserInput in) {
        return managerUserService.listByParams(in);
    }

    @PostMapping("/add")
    public ResponseResult<Object> addManagerUser(@Validated({Insert.class}) ManagerUserInput in) {
        managerUserService.addManagerUser(in);
        return ResponseResult.success();
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyManagerUser(@Validated({Update.class}) ManagerUserInput in) {
        managerUserService.modifyManagerUserById(in);
        return ResponseResult.success();
    }
}
