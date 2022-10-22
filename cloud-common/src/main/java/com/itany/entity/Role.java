package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Role.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 8062858038005316748L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
}
