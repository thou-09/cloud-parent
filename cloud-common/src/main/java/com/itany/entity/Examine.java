package com.itany.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Examine.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_examine")
public class Examine implements Serializable {

    private static final long serialVersionUID = -203374846690910275L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 审核标题
     */
    private String title;
    /**
     * 服务/加盟商名称
     */
    private String name;
    /**
     * 服务类型
     */
    private Integer typeid;
    /**
     * 提交日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 申请人 ID
     */
    private Integer userid;
    /**
     * 服务商成立日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date commpanycreatedate;
    /**
     * 地址
     */
    private String address;
    /**
     * 规模
     */
    private String scale;
    /**
     * 坐标
     */
    private String gps;
    /**
     * 审核类型，加盟审核 1 服务审核 2
     */
    private Integer examinetype;
    /**
     * 状态，启用 1 禁用 0
     */
    private Integer flag;
    /**
     * 服务范围格式（服务类型 ID,服务类型 ID..）形式存储
     */
    private String servertype;
    /**
     * 服务地区格式以（地区 ID,地区 ID..）形式存储
     */
    private String serverarea;
    /**
     * 服务商简介/服务简介
     */
    private String info;
    /**
     * 若是服务审核则存储发布人所在服务商 ID，否则为 null
     */
    private Integer commpanyid;
    /**
     * 备注
     */
    private String examineInfo;
    /**
     * 价格
     */
    private BigDecimal price;
}
