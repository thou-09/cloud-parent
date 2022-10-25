package com.itany.mapper;

import com.itany.dto.TypeDTO;
import com.itany.input.TypeInput;
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
     * 根据条件查询 Types
     *
     * @param in -
     * @return java.util.List<com.itany.dto.TypeDTO>
     * @author Thou
     * @date 2022/10/24
     */
    List<TypeDTO> listAllByParams(TypeInput in);

    /**
     * 根据 id 修改 Type
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void updateTypeById(TypeInput in);

    /**
     * 根据 id 查询 Type
     *
     * @param id -
     * @return com.itany.dto.TypeDTO
     * @author Thou
     * @date 2022/10/25
     */
    TypeDTO getTypeById(@Param("id") Integer id);

    /**
     * 新增 Type
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void insertType(TypeInput in);

    /**
     * 根据 id 删除 Type
     *
     * @param id -
     * @author Thou
     * @date 2022/10/25
     */
    void deleteTypeById(@Param("id") Integer id);

    /**
     * 批量删除
     *
     * @param list -
     * @author Thou
     * @date 2022/10/25
     */
    void deleteTypeBatch(@Param("list") List<Integer> list);

    /**
     * 查找当前 Type 与 ServerType 的联系，parentid == null 找子联系，parentid != null 找自联系
     *
     * @param in -
     * @return java.util.List<java.lang.Integer>
     * @author Thou
     * @date 2022/10/25
     */
    List<Integer> selectRelation(TypeInput in);
}
