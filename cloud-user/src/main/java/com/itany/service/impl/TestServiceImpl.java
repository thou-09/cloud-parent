package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.dao.ITestDao;
import com.itany.entity.User;
import com.itany.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class TestServiceImpl implements ITestService {

    @Autowired
    private ITestDao testDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageInfo<User> findUserAll(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<User> list = testDao.selectUserAll();
        PageInfo<User> info =new PageInfo<User>(list);
        return info;
    }
}
