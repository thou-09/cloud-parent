package com.itany.dao;

import com.itany.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SsoDao.
 *
 * @author Thou
 * @date 2022/10/31
 */
@Repository
public interface SsoDao {

    /**
     * 根据用户名和密码查询 User
     *
     * @param name -
     * @param password -
     * @return com.itany.dto.UserDTO
     * @author Thou
     * @date 2022/10/31
     */
    UserDTO findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
