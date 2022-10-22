package com.itany.dao;

import com.itany.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ITestDao {

    public List<User> selectUserAll();
}
