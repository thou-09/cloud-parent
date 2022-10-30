package com.itany.mapper;

import com.itany.dto.ManagerUserDTO;
import com.itany.dto.UserRoleDTO;
import com.itany.input.ManagerUserInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ManagerUserMapper.
 *
 * @author Thou
 * @date 2022/10/18
 */
public interface ManagerUserMapper {

    /**
     * 根据用户名和密码查询单个 ManagerUser
     *
     * @param input -
     * @return com.itany.dto.ManagerUserDTO
     * @author Thou
     * @date 2022/10/19
     */
    ManagerUserDTO getOneByUsernameAndPassword(ManagerUserInput input);

    /**
     * 查询所有 ManagerUser with Role
     *
     * @return java.util.List<com.itany.dto.ManagerUserDTO>
     * @author Thou
     * @date 2022/10/29
     */
    List<ManagerUserDTO> listAllWithRole();

    /**
     * 根据 id 修改 ManagerUser
     *
     * @param in -
     * @author Thou
     * @date 2022/10/29
     */
    void updateById(ManagerUserInput in);

    /**
     * 新增 ManagerUsr
     *
     * @param in -
     * @author Thou
     * @date 2022/10/29
     */
    void insertManagerUser(ManagerUserInput in);

    /**
     * 批量新增 userRoles
     *
     * @param list -
     * @author Thou
     * @date 2022/10/29
     */
    void insertUserRoleBatch(@Param("list") List<UserRoleDTO> list);

    /**
     * 根据 user 删除 userRole
     *
     * @param userid -
     * @author Thou
     * @date 2022/10/29
     */
    void deleteUserRoleByUserid(@Param("userid") Integer userid);
}
