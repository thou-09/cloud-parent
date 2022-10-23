package com.itany.controllers;

import com.itany.input.ServerInfoInput;
import com.itany.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ServerInfoController.
 *
 * @author Thou
 * @date 2022/10/23
 */
@RestController
@RequestMapping("/servers")
public class ServerInfoController {

    @Autowired
    private ServerInfoService serverInfoService;

    @RequestMapping("/list")
    public Map<String, Object> listServers(ServerInfoInput in) {
        return serverInfoService.listAllByParams(in);
    }


}
