package com.itany.service.impl;

import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dao.SsoDao;
import com.itany.dto.UserDTO;
import com.itany.exception.AppException;
import com.itany.input.SsoInput;
import com.itany.input.UserInput;
import com.itany.service.SsoService;
import com.itany.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * SsoServiceImpl.
 *
 * @author Thou
 * @date 2022/10/31
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SsoServiceImpl implements SsoService {

    @Autowired
    private SsoDao ssoDao;

    @Override
    public UserDTO loginUser(SsoInput input) {
        if (!"DWSE".equalsIgnoreCase(input.getCheckCode())) {
            throw new AppException(AppExceptionMsgEnum.USER_CHECKCODE_IS_ERROR);
        }

        input.setPassword(Md5Utils.md5(input.getPassword()));
        return Optional.ofNullable(ssoDao.findByNameAndPassword(input.getName(), input.getPassword()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.USER_NOT_EXIST));
    }
}
