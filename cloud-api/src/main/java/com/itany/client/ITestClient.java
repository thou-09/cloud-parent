package com.itany.client;

import com.github.pagehelper.PageInfo;

import com.itany.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cloud-user")
public interface ITestClient {

    @RequestMapping("/test/findUsers")
    public  PageInfo<User> findUsers(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                      @RequestParam(name = "rows",defaultValue = "10") Integer rows);

}
