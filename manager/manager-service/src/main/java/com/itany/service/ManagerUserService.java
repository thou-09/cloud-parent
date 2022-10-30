package com.itany.service;

import com.itany.dto.ManagerUserDTO;
import com.itany.input.ManagerUserInput;

import java.util.Map;

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

    /**
     * 根据条件查询 ManagerUsers
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/29
     */
    Map<String, Object> listByParams(ManagerUserInput in);

    /**
     * 新增 ManagerUser
     *
     * @param in -
     * @author Thou
     * @date 2022/10/29
     */
    void addManagerUser(ManagerUserInput in);

    /**
     * 根据 id 修改 ManagerUser
     *
     * @param in -
     * @author Thou
     * @date 2022/10/29
     */
    void modifyManagerUserById(ManagerUserInput in);
}
