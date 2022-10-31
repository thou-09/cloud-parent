package com.itany.client;

import com.itany.input.UserInput;
import com.itany.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserClient.
 *
 * @author Thou
 * @date 2022/10/31
 */
@Component
@FeignClient(value = "cloud-user")
public interface UserClient {

    /**
     * 用户注册
     *
     * @param input -
     * @return com.itany.vo.ResponseResult<java.lang.Object>
     * @author Thou
     * @date 2022/10/31
     */
    @RequestMapping("/user/register")
    ResponseResult<Object> userRegister(UserInput input);
}
