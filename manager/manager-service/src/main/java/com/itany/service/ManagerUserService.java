package com.itany.service;

import com.itany.input.ManagerUserInput;
import com.itany.vo.ManagerUserVO;

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
     * @return com.itany.vo.ManagerUserVO
     * @author Thou
     * @date 2022/10/18
     */
    ManagerUserVO login(ManagerUserInput input);
}
