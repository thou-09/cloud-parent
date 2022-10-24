package com.itany.mapper;

import com.itany.dto.UserDTO;
import com.itany.input.UserInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserMapper.
 *
 * @author Thou
 * @date 2022/10/19
 */
public interface UserMapper {

    /**
     * 根据条件查询 Users
     *
     * @param in -
     * @return java.util.List<com.itany.dto.UserDTO>
     * @author Thou
     * @date 2022/10/19
     */
    List<UserDTO> listAllByParams(UserInput in);

    /**
     * 根据 id 修改 User
     *
     * @param in -
     * @author Thou
     * @date 2022/10/19
     */
    void updateUserById(UserInput in);

    /**
     * 根据 id 查询 User
     *
     * @param id -
     * @return com.itany.dto.UserDTO
     * @author Thou
     * @date 2022/10/19
     */
    UserDTO getUserById(@Param("id") Integer id);

    /**
     * 根据 phone 查询 User
     *
     * @param phone -
     * @return com.itany.dto.UserDTO
     * @author Thou
     * @date 2022/10/24
     */
    UserDTO getUserByPhone(@Param("phone") String phone);
}
