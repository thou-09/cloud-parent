package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Permission.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 8292131261032999525L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父权限主键
     */
    private Integer parentid;
    /**
     * url
     */
    private String url;
}
