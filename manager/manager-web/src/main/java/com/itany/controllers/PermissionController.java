package com.itany.controllers;

import com.itany.config.AuthorityCacheManager;
import com.itany.dto.ManagerUserDTO;
import com.itany.dto.PermissionDTO;
import com.itany.input.PermissionInput;
import com.itany.service.PermissionService;
import com.itany.vo.MenuVO;
import com.itany.vo.PermissionVO;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PermissionController.
 *
 * @author Thou
 * @date 2022/10/27
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/list")
    public Map<String, Object> listPersByParams(PermissionInput in) {
        return permissionService.listAllByParams(in);
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyPer(PermissionInput in) {
        permissionService.updateById(in);
        return ResponseResult.success();
    }

    @PostMapping("/tree")
    public List<PermissionVO> listPerTree() {
        return permissionService.listTree();
    }

    @PostMapping("/menus")
    public List<MenuVO> showMenus(HttpSession session) {
        ManagerUserDTO loginUser = (ManagerUserDTO)session.getAttribute("loginUser");
        List<Integer> rids = AuthorityCacheManager.USER_ROLE_MAP.get(loginUser.getId());
        List<PermissionDTO> permission = AuthorityCacheManager.ROLE_PERMISSION_MAP.get(rids.get(0));
        List<MenuVO> menus = new ArrayList<>();
        permission.forEach(per -> {
            MenuVO menu = new MenuVO();
            menu.setMenuid(per.getId() + "");
            menu.setIcon("icon-sys");
            menu.setUrl(per.getUrl());
            menu.setMenuname(per.getName());
            List<MenuVO> sons = new ArrayList<>();
            per.getSonsPermission().forEach(sper -> {
                MenuVO son = new MenuVO();
                son.setMenuid(sper.getId() + "");
                son.setIcon("icon-nav");
                son.setUrl(sper.getUrl());
                son.setMenuname(sper.getName());
                sons.add(son);
            });
            menu.setMenus(sons);
            menus.add(menu);
        });
        return menus;
    }
}
