package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.dto.MessageDTO;
import com.itany.input.MessageInput;
import com.itany.mapper.MessageMapper;
import com.itany.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MessageService.
 *
 * @author Thou
 * @date 2022/10/19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> listAllByParams(MessageInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<MessageDTO> messageDTOList = messageMapper.listAllByParams(in);
        PageInfo<MessageDTO> info = new PageInfo<>(messageDTOList);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", messageDTOList);
        return map;
    }
}
