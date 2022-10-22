package com.itany.client;

import com.github.pagehelper.PageInfo;

import com.itany.entity.User;
import com.itany.vo.ActionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "cloud-user")
public interface ITestClient {

    @RequestMapping("/test/findUsers")
    public  PageInfo<User> findUsers(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                      @RequestParam(name = "rows",defaultValue = "10") Integer rows);

}
