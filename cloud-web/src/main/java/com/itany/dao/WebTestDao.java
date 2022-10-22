package com.itany.dao;

import com.itany.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface WebTestDao {

    public User selectUser(User user);
}
