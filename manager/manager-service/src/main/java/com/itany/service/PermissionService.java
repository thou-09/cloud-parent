package com.itany.service;

import com.itany.dto.PermissionDTO;
import com.itany.input.PermissionInput;
import com.itany.vo.PermissionVO;

import java.util.List;
import java.util.Map;

/**
 * PermissionService.
 *
 * @author Thou
 * @date 2022/10/27
 */
public interface PermissionService {

    /**
     * 根据条件查询 Permissions
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/27
     */
    Map<String, Object> listAllByParams(PermissionInput in);

    /**
     * 根据 id 更新 Permission
     *
     * @param in -
     * @author Thou
     * @date 2022/10/27
     */
    void updateById(PermissionInput in);

    /**
     * 查询 Permission Tree
     *
     * @return java.util.List<com.itany.vo.PermissionVO>
     * @author Thou
     * @date 2022/10/27
     */
    List<PermissionVO> listTree();
}
