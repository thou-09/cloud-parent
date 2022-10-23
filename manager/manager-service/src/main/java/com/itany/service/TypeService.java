package com.itany.service;

import com.itany.dto.TypeDTO;

import java.util.List;

/**
 * TypeService.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface TypeService {

    /**
     * 根据 type 查询 types
     *
     * @param type -
     * @return java.util.List<com.itany.dto.TypeDTO>
     * @author Thou
     * @date 2022/10/23
     */
    List<TypeDTO> listTypeByType(Integer type);
}
