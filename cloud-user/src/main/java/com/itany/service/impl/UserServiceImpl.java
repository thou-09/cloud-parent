package com.itany.service.impl;

import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dao.UserDao;
import com.itany.exception.AppException;
import com.itany.input.UserInput;
import com.itany.service.UserService;
import com.itany.util.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImpl.
 *
 * @author Thou
 * @date 2022/10/31
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(UserInput input) {
        if (!"DWSE".equalsIgnoreCase(input.getCheckCode())) {
            throw new AppException(AppExceptionMsgEnum.USER_CHECKCODE_IS_ERROR);
        }
        input.setPassword(Md5Utils.md5(input.getPassword()));
        input.setNickname("用户" + input.getName());
        input.setFlag(AppConsts.STATUS_ENABLE);
        input.setHeadimg(AppConsts.DEFAULT_HEADIMG);
        userDao.insertUser(input);
    }
}
