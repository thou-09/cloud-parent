package com.itany.service.impl;

import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.TypeDTO;
import com.itany.exception.AppException;
import com.itany.input.TypeInput;
import com.itany.mapper.TypeMapper;
import com.itany.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Map<String, Object> listAllByParams(TypeInput in) {
        List<TypeDTO> dtos = typeMapper.listAllByParams(in);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", dtos.size());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public void updateTypeById(TypeInput in) {
        Optional.ofNullable(typeMapper.getTypeById(in.getId()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.TYPE_NOT_EXIST));
        typeMapper.updateTypeById(in);
    }

    @Override
    public void insertType(TypeInput in) {
        typeMapper.insertType(in);
    }

    @Override
    public void deleteTypeById(TypeInput in) {
        List<Integer> ids = typeMapper.selectRelation(in);
        if (ids.size() == 1 && null == ids.get(0)) {
            if (null == in.getParentid()) {
                TypeInput typeInput = new TypeInput();
                typeInput.setParentid(in.getId());
                List<Integer> removeIds = typeMapper.listAllByParams(typeInput)
                        .stream()
                        .map(TypeDTO::getId)
                        .collect(Collectors.toList());
                // 先删子类型
                typeMapper.deleteTypeBatch(removeIds);
                // 再删自己
                typeMapper.deleteTypeById(in.getId());
            } else {
                // 直接删自己
                typeMapper.deleteTypeById(in.getId());
            }
        } else {
            throw new AppException(AppExceptionMsgEnum.TYPE_HAS_RELATION);
        }
    }
}
