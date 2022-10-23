package com.itany.service;

import com.itany.dto.ManagerUserDTO;
import com.itany.input.ManagerUserInput;

/**
 * ManagerUserService.
 *
 * @author Thou
 * @date 2022/10/18
 */
public interface ManagerUserService {

    /**
     * 登录
     *
     * @param input -
     * @return com.itany.dto.ManagerUserDTO
     * @author Thou
     * @date 2022/10/18
     */
    ManagerUserDTO login(ManagerUserInput input);
}
