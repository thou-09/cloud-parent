package com.itany.service;

import com.itany.dto.UserDTO;
import com.itany.input.SsoInput;

/**
 * SsoService.
 *
 * @author Thou
 * @date 2022/10/31
 */
public interface SsoService {

    /**
     * 用户登录
     *
     * @param input -
     * @return com.itany.dto.UserDTO
     * @author Thou
     * @date 2022/10/31
     */
    UserDTO loginUser(SsoInput input);
}
