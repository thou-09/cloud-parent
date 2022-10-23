package com.itany.service.impl;

import com.itany.dto.TypeDTO;
import com.itany.mapper.TypeMapper;
import com.itany.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TypeServiceImpl.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TypeServiceImpl implements TypeService {

    @Autowired
    public TypeMapper typeMapper;

    @Override
    public List<TypeDTO> listTypeByType(Integer type) {
        return typeMapper.listTypeByType(type);
    }
}
