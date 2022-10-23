package com.itany.mapper;

import com.itany.dto.TypeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TypeMapper.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface TypeMapper {

    /**
     * 根据 ids 查询 Types
     *
     * @param ids -
     * @return java.util.List<com.itany.dto.TypeDTO>
     * @author Thou
     * @date 2022/10/23
     */
    List<TypeDTO> listTypesByIds(@Param("ids") List<String> ids);

    /**
     * 根据 type 查询 Types
     *
     * @param type -
     * @return java.util.List<com.itany.dto.TypeDTO>
     * @author Thou
     * @date 2022/10/23
     */
    List<TypeDTO> listTypeByType(@Param("type") Integer type);
}
