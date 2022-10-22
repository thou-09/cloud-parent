package com.itany.service.impl;

import com.itany.dao.WebTestDao;
import com.itany.entity.User;
import com.itany.service.IWebTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class WebTestServiceImpl implements IWebTestService {

    @Autowired
    private WebTestDao webTestDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public User findUser(User user) {
        return webTestDao.selectUser(user);
    }
}
