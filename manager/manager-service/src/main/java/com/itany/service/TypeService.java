package com.itany.service;

import com.itany.input.TypeInput;

import java.util.Map;

/**
 * TypeService.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface TypeService {

    /**
     * 根据条件查询 Types
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/24
     */
    Map<String, Object> listAllByParams(TypeInput in);

    /**
     * 根据 id 修改 Type
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void updateTypeById(TypeInput in);

    /**
     * 添加 Type
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void insertType(TypeInput in);

    /**
     * 删除 Type
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void deleteTypeById(TypeInput in);
}
