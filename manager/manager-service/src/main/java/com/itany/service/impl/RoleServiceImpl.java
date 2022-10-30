package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.dto.RoleDTO;
import com.itany.dto.RolePermissionDTO;
import com.itany.input.RoleInput;
import com.itany.mapper.RoleMapper;
import com.itany.service.RoleService;
import com.itany.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RoleServiceImpl.
 *
 * @author Thou
 * @date 2022/10/27
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> listAllByParam(RoleInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<RoleDTO> dtos = roleMapper.listAllByParam(in);
        PageInfo<RoleDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public void updateRoleById(RoleInput in) {
        List<RolePermissionDTO> rolePers = new ArrayList<>();
        List<Integer> permissionIds = in.getPermissionIds();
        if (null == permissionIds) {
            roleMapper.deleteRolePermissionByRoleId(in.getId());
        } else {
            permissionIds.forEach(id -> {
                RolePermissionDTO rolePer = new RolePermissionDTO();
                rolePer.setRoleid(in.getId());
                rolePer.setPermissionid(id);
                rolePers.add(rolePer);
            });
            roleMapper.deleteRolePermissionByRoleId(in.getId());
            roleMapper.insertRolePermissionBatch(rolePers);
        }
        roleMapper.updateRoleById(in);
    }

    @Override
    public List<RoleVO> listRoleSimple() {
        return roleMapper.listRoleSimple();
    }
}
