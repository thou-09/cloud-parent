package com.itany.service;

import com.itany.dto.UserDTO;
import com.itany.input.UserInput;

import java.util.Map;

/**
 * UserService.
 *
 * @author Thou
 * @date 2022/10/19
 */
public interface UserService {

    /**
     * 根据条件分页查询 Users
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/19
     */
    Map<String, Object> listAllByParams(UserInput in);

    /**
     * 根据 id 修改 User
     *
     * @param input -
     * @author Thou
     * @date 2022/10/19
     */
    void updateUserById(UserInput input);

    /**
     * 根据 id 查询 User
     *
     * @param id -
     * @return com.itany.dto.UserDTO
     * @author Thou
     * @date 2022/10/19
     */
    UserDTO getUserById(Integer id);

    /**
     * 根据 phone 绑定子账号
     *
     * @param in -
     * @author Thou
     * @date 2022/10/24
     */
    void addSub(UserInput in);
}
