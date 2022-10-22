package com.itany.service.impl;

import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.ManagerUserDTO;
import com.itany.exception.AppException;
import com.itany.input.ManagerUserInput;
import com.itany.mapper.ManagerUserMapper;
import com.itany.service.ManagerUserService;
import com.itany.util.Md5Utils;
import com.itany.vo.ManagerUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public ManagerUserVO login(ManagerUserInput input) {
        input.setPassword(Md5Utils.md5(input.getPassword()));

        ManagerUserDTO dto = Optional.ofNullable(managerUserMapper.getOneByUsernameAndPassword(input))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.USERNAME_AND_PASSWORD_ERROR));

        ManagerUserVO vo = new ManagerUserVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }
}
