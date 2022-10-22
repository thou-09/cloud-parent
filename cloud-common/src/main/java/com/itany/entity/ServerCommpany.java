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
 * ServerCommpany.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_server_commpany")
public class ServerCommpany implements Serializable {

    private static final long serialVersionUID = 386303721433133250L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 服务公司名称
     */
    private String name;
    /**
     * 服务公司简介
     */
    private String info;
    /**
     * 地址
     */
    private String address;
    /**
     * 坐标（经度，纬度）
     */
    private String gps;
    /**
     * 规模
     */
    private String scale;
    /**
     * 成立日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
    /**
     * 入驻日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date joindate;
    /**
     * 状态，启用 1 禁用 0
     */
    private Integer flag;
    /**
     * 提供商类型，生活 1 商务服务 2
     */
    private Integer type;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 公司电话
     */
    private String phone;
}
