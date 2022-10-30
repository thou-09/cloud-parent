package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.dto.PermissionDTO;
import com.itany.input.PermissionInput;
import com.itany.mapper.PermissionMapper;
import com.itany.service.PermissionService;
import com.itany.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PermissionServiceImpl.
 *
 * @author Thou
 * @date 2022/10/27
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionmapper;

    @Override
    public Map<String, Object> listAllByParams(PermissionInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<PermissionDTO> dtos = permissionmapper.listAllByParams(in);
        PageInfo<PermissionDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public void updateById(PermissionInput in) {
        permissionmapper.updateById(in);
    }

    @Override
    public List<PermissionVO> listTree() {
        return permissionmapper.listTree();
    }
}
