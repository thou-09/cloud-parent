package com.itany.service;

import com.itany.input.RoleInput;
import com.itany.vo.RoleVO;

import java.util.List;
import java.util.Map;

/**
 * RoleService.
 *
 * @author Thou
 * @date 2022/10/27
 */
public interface RoleService {

    /**
     * 根据条件查询 Roles
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/27
     */
   Map<String, Object> listAllByParam(RoleInput in);

   /**
    * 根据 id 修改 Role
    *
    * @param in -
    * @author Thou
    * @date 2022/10/29
    */
   void updateRoleById(RoleInput in);

   /**
    * 只查询 Roles，不附带其他信息
    *
    * @return java.util.List<com.itany.vo.RoleVO>
    * @author Thou
    * @date 2022/10/29
    */
   List<RoleVO> listRoleSimple();
}
