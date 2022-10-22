package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Area.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 3982857099283851746L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 地区名称
     */
    private String name;
    /**
     * 父级地区主键
     */
    private Integer parentid;
}
