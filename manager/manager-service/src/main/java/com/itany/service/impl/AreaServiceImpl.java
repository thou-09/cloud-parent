package com.itany.service.impl;

import com.itany.dto.AreaDTO;
import com.itany.mapper.AreaMapper;
import com.itany.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AreaServiceImpl.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<AreaDTO> listAll() {
        return areaMapper.listAreas();
    }
}
