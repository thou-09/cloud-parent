package com.itany.service;

import com.itany.dto.AreaDTO;

import java.util.List;

/**
 * AreaService.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface AreaService {

    /**
     * 查询所有地区
     *
     * @return java.util.List<com.itany.dto.AreaDTO>
     * @author Thou
     * @date 2022/10/23
     */
    List<AreaDTO> listAll();
}
