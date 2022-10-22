package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ManagerUser.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_manager_user")
public class ManagerUser implements Serializable {

    private static final long serialVersionUID = 765855524816924139L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 服务商主键，若是平台人员则为 null
     */
    private Integer commpanyid;
}
