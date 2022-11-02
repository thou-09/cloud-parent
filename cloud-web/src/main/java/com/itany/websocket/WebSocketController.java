package com.itany.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WebSocketController.
 *
 * @author Thou
 * @date 2022/11/2
 */
@Controller
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @RequestMapping("/payback")
    @ResponseBody
    public String payback(@RequestParam(value = "userid") String userid) {
        webSocketService.sendMessage("paysuccess", userid);
        return "ok";
    }
}