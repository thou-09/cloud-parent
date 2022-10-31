package com.itany.dao;

import com.itany.input.UserInput;
import org.springframework.stereotype.Repository;

/**
 * UserDao.
 *
 * @author Thou
 * @date 2022/10/31
 */
@Repository
public interface UserDao {

    /**
     * 新增用户
     *
     * @param input -
     * @author Thou
     * @date 2022/10/31
     */
    void insertUser(UserInput input);
}
