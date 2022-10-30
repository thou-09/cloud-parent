package com.itany.mapper;

import com.itany.dto.RoleDTO;
import com.itany.dto.RolePermissionDTO;
import com.itany.input.RoleInput;
import com.itany.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * RoleMapper.
 *
 * @author Thou
 * @date 2022/10/27
 */
public interface RoleMapper {

    /**
     * 根据条件查询 Roles
     *
     * @param in -
     * @return java.util.List<com.itany.dto.RoleDTO>
     * @author Thou
     * @date 2022/10/27
     */
    List<RoleDTO> listAllByParam(RoleInput in);

    /**
     * 根据 id 修改 Role
     *
     * @param in -
     * @author Thou
     * @date 2022/10/29
     */
    void updateRoleById(RoleInput in);

    /**
     * 根据 roleid 删除 role permission
     *
     * @param roleid -
     * @author Thou
     * @date 2022/10/29
     */
    void deleteRolePermissionByRoleId(@Param("roleid") Integer roleid);

    /**
     * 批量新增 role permission
     *
     * @param list -
     * @author Thou
     * @date 2022/10/29
     */
    void insertRolePermissionBatch(@Param("list") List<RolePermissionDTO> list);

    /**
     * 只查询 Roles，不附带其他信息
     *
     * @return java.util.List<com.itany.vo.RoleVO>
     * @author Thou
     * @date 2022/10/29
     */
    List<RoleVO> listRoleSimple();

    /**
     * 查询角色与权限之间的对应关系
     *
     * @return java.util.List<com.itany.dto.RoleDTO>
     * @author Thou
     * @date 2022/10/30
     */
    List<RoleDTO> amOne();

    /**
     * 查询角色与 URI 之间的关系
     *
     * @return java.util.List<com.itany.dto.RoleDTO>
     * @author Thou
     * @date 2022/10/30
     */
    List<RoleDTO> amFour();
}
