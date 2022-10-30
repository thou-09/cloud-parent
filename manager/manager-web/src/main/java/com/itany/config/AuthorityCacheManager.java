package com.itany.config;

import com.itany.dto.PermissionDTO;
import com.itany.dto.RoleDTO;
import com.itany.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AuthorityCacheManager.
 *
 * @author Thou
 * @date 2022/10/30
 */
@Component
public class AuthorityCacheManager implements ApplicationRunner {

    /**
     * 角色与 URI 的对应关系
     */
    public static Map<Integer, List<String>> ROLE_URI_MAP = null;
    /**
     * 角色与权限的对应关系
     */
    public static final Map<Integer, List<PermissionDTO>> ROLE_PERMISSION_MAP = new HashMap<>(8);
    /**
     * 用户和角色之间的对应关系
     */
    public static final Map<Integer, List<Integer>> USER_ROLE_MAP = new HashMap<>(8);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<RoleDTO> roles = roleMapper.amOne();
        roles.forEach(role -> {
            List<PermissionDTO> pers = role.getPermissions().stream()
                    .filter(per -> null == per.getParentid()).collect(Collectors.toList());
            Map<Integer, List<PermissionDTO>> map = role.getPermissions().stream()
                    .filter(per -> null != per.getParentid())
                    .collect(Collectors.groupingBy(PermissionDTO::getParentid));
            pers.forEach(per -> {
                per.setSonsPermission(map.get(per.getId()));
            });
            ROLE_PERMISSION_MAP.put(role.getId(), pers);
        });
        List<RoleDTO> roleUrls = roleMapper.amFour();
        ROLE_URI_MAP = roleUrls.stream().collect(Collectors.toMap(RoleDTO::getId, roleDTO -> {
            List<String> list = new ArrayList<>();
            list.add(roleDTO.getUrl());
            return list;
        }, (url1, url2) -> {
            url1.addAll(url2);
            return url1;
        }));
    }

    public static void remove(Integer key) {
        USER_ROLE_MAP.keySet().removeIf(s -> s.equals(key));
    }
}
