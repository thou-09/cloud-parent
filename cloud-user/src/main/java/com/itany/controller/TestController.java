package com.itany.controller;

import com.github.pagehelper.PageInfo;
import com.itany.entity.User;
import com.itany.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping("/findUsers")
    public PageInfo<User> findUsers(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                    @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        PageInfo<User> info = testService.findUserAll(1,10);
        return info;
    }



}
