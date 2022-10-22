package com.itany.service;

import com.itany.input.MessageInput;

import java.util.Map;

/**
 * MessageService.
 *
 * @author Thou
 * @date 2022/10/19
 */
public interface MessageService {

    /**
     * 根据条件分页查询 Messages
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/19
     */
    Map<String, Object> listAllByParams(MessageInput in);
}
