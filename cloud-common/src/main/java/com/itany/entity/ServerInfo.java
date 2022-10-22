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
 * ServerInfo.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_server_info")
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = -6075668995576267022L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 服务名称
     */
    private String servername;
    /**
     * 简介
     */
    private String info;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * userid
     */
    private Integer userid;
    /**
     * 发布公司id
     */
    private Integer commpanyid;
    /**
     * 发布日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
    /**
     * 状态，启用 1 禁用 0
     */
    private Integer flag;
}
