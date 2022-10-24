package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.UserDTO;
import com.itany.exception.AppException;
import com.itany.input.UserInput;
import com.itany.mapper.UserMapper;
import com.itany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * UserServiceImpl.
 *
 * @author Thou
 * @date 2022/10/19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> listAllByParams(UserInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<UserDTO> userDTOList = userMapper.listAllByParams(in);
        PageInfo<UserDTO> info = new PageInfo<>(userDTOList);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", userDTOList);
        return map;
    }

    @Override
    public void updateUserById(UserInput in) {
        Optional.ofNullable(userMapper.getUserById(in.getId()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.USER_NOT_EXIST));
        userMapper.updateUserById(in);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDTO getUserById(Integer id) {
        return Optional.ofNullable(userMapper.getUserById(id))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.USER_NOT_EXIST));
    }

    @Override
    public void addSub(UserInput in) {
        UserDTO dto = Optional.ofNullable(userMapper.getUserByPhone(in.getPhone()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.USER_NOT_EXIST));
        Integer dtoCompanyid = dto.getCompanyid();
        if (null != dtoCompanyid) {
            if (dtoCompanyid.equals(in.getCompanyid())) {
                throw new AppException(AppExceptionMsgEnum.USER_HAS_BINDED_OWN);
            } else {
                throw new AppException(AppExceptionMsgEnum.USER_HAS_BINDED_OTHER);
            }
        }

        UserInput userInput = new UserInput();
        userInput.setCompanyid(in.getCompanyid());
        userInput.setId(dto.getId());
        userMapper.updateUserById(userInput);
    }
}
