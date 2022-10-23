package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.dto.ServerInfoDTO;
import com.itany.input.ServerInfoInput;
import com.itany.mapper.ServerInfoMapper;
import com.itany.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ServerInfoServiceImpl.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ServerInfoServiceImpl implements ServerInfoService {

    @Autowired
    private ServerInfoMapper serverInfoMapper;

    @Override
    public Map<String, Object> listAllByParams(ServerInfoInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<ServerInfoDTO> dtos = serverInfoMapper.listAllByParams(in);
        PageInfo<ServerInfoDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }
}
