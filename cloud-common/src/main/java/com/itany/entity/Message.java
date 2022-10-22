package com.itany.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Message.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 140934990514194042L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 称呼
     */
    private String name;
    /**
     * 留言内容
     */
    private String msg;
    /**
     * 联系号码
     */
    private String phone;
    /**
     * 意向范围
     */
    private String area;
    /**
     * 留言服务商主键
     */
    private Integer commpanyid;
    /**
     * 留言时间
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
}
