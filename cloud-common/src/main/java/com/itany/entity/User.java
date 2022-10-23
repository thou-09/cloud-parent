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
 * User.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_user")
public class User implements Serializable{

    private static final long serialVersionUID = 2880225495665196762L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机
     */
    private String phone;
    /**
     * 微信
     */
    private String wechat;
    /**
     * qq
     */
    private String qq;
    /**
     * 头像
     */
    private String headimg;
    /**
     * 性别，男 1 女 2
     */
    private Integer sex;
    /**
     * 加盟商主键，服务商有值普通用户为 NULL
     */
    private Integer companyid;
    /**
     * 状态，启用 1 禁用 0
     */
    private Integer flag;
    /**
     * 注册时间
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
}
