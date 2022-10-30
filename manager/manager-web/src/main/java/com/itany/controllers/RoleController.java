package com.itany.controllers;

import com.itany.input.RoleInput;
import com.itany.service.RoleService;
import com.itany.vo.ResponseResult;
import com.itany.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * RoleController.
 *
 * @author Thou
 * @date 2022/10/27
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    public Map<String, Object> listRoles(RoleInput in) {
        return roleService.listAllByParam(in);
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyRoles(RoleInput in) {
        roleService.updateRoleById(in);
        return ResponseResult.success();
    }

    @PostMapping("/simple")
    public List<RoleVO> listRoleSimple() {
        return roleService.listRoleSimple();
    }
}
