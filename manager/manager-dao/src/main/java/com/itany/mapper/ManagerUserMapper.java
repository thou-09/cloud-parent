package com.itany.mapper;

import com.itany.dto.ManagerUserDTO;
import com.itany.input.ManagerUserInput;

/**
 * ManagerUserMapper.
 *
 * @author Thou
 * @date 2022/10/18
 */
public interface ManagerUserMapper {

    /**
     * 根据用户名和密码查询单个 ManagerUser
     *
     * @param input -
     * @return com.itany.dto.ManagerUserDTO
     * @author Thou
     * @date 2022/10/19
     */
    ManagerUserDTO getOneByUsernameAndPassword(ManagerUserInput input);
}
