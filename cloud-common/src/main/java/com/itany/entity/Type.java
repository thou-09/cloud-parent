package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Type.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1916322468887412998L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 父类型主键
     */
    private Integer parentid;
    /**
     * 服务类型的类型，生活 1 商务 2
     */
    private Integer type;
}
