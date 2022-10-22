package com.itany.mapper;

import com.itany.dto.MessageDTO;
import com.itany.input.MessageInput;

import java.util.List;

/**
 * MessageMapper.
 *
 * @author Thou
 * @date 2022/10/19
 */
public interface MessageMapper {

    /**
     * 根据条件分页查询 Users
     *
     * @param in -
     * @return java.util.List<com.itany.dto.MessageDTO>
     * @author Thou
     * @date 2022/10/19
     */
    List<MessageDTO> listAllByParams(MessageInput in);
}
