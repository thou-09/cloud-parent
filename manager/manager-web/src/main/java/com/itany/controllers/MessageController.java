package com.itany.controllers;

import com.itany.input.MessageInput;
import com.itany.service.MessageService;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * MessageController.
 *
 * @author Thou
 * @date 2022/10/19
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messagelist")
    public Map<String, Object> listMessage(MessageInput in) {
        return messageService.listAllByParams(in);
    }
}
