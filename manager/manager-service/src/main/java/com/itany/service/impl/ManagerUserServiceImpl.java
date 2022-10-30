package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.ManagerUserDTO;
import com.itany.dto.UserRoleDTO;
import com.itany.exception.AppException;
import com.itany.input.ManagerUserInput;
import com.itany.mapper.ManagerUserMapper;
import com.itany.service.ManagerUserService;
import com.itany.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * ManagerUserServiceImpl.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ManagerUserServiceImpl implements ManagerUserService {

    @Autowired
    private ManagerUserMapper managerUserMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ManagerUserDTO login(ManagerUserInput input) {
        input.setPassword(Md5Utils.md5(input.getPassword()));

        return Optional.ofNullable(managerUserMapper.getOneByUsernameAndPassword(input))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.USERNAME_AND_PASSWORD_ERROR));
    }

    @Override
    public Map<String, Object> listByParams(ManagerUserInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<ManagerUserDTO> dtos = managerUserMapper.listAllWithRole();
        PageInfo<ManagerUserDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public void addManagerUser(ManagerUserInput in) {
        in.setPassword(Md5Utils.md5(in.getPassword()));
        managerUserMapper.insertManagerUser(in);
        List<Integer> roleIds = in.getRoleIds();
        if (null != roleIds) {
            List<UserRoleDTO> userRoles = new ArrayList<>();
            roleIds.forEach(rid -> {
                UserRoleDTO userRole = new UserRoleDTO();
                userRole.setUserid(in.getId());
                userRole.setRoleid(rid);
                userRoles.add(userRole);
            });
            managerUserMapper.insertUserRoleBatch(userRoles);
        }
    }

    @Override
    public void modifyManagerUserById(ManagerUserInput in) {
        if (null != in.getPassword() && !"".equals(in.getPassword())) {
            in.setPassword(Md5Utils.md5(in.getPassword()));
        }
        managerUserMapper.updateById(in);
        List<Integer> roleIds = in.getRoleIds();
        if (null != roleIds) {
            managerUserMapper.deleteUserRoleByUserid(in.getId());
            List<UserRoleDTO> userRoles = new ArrayList<>();
            roleIds.forEach(rid -> {
                UserRoleDTO userRole = new UserRoleDTO();
                userRole.setUserid(in.getId());
                userRole.setRoleid(rid);
                userRoles.add(userRole);
            });
            managerUserMapper.insertUserRoleBatch(userRoles);
        }
    }
}
