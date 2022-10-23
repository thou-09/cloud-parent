package com.itany.mapper;

import com.itany.dto.AreaDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AreaMapper.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface AreaMapper {

    /**
     * 根据 ids 查询 Areas
     *
     * @param ids -
     * @return java.util.List<com.itany.dto.AreaDTO>
     * @author Thou
     * @date 2022/10/23
     */
    List<AreaDTO> listAreasByIds(@Param("ids") List<String> ids);
}
