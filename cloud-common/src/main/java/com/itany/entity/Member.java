package com.itany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Member.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_member")
public class Member implements Serializable {

    private static final long serialVersionUID = 6297513791248061680L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 会员等级
     */
    private String level;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 会员天数
     */
    private String day;
}
