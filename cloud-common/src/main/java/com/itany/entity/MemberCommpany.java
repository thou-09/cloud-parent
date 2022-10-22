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
 * MemberCommpany.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_member_commpany")
public class MemberCommpany implements Serializable {

    private static final long serialVersionUID = -2635412316418358516L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 会员等级主键
     */
    private Integer memberid;
    /**
     * 服务商主键
     */
    private Integer commpanyid;
    /**
     * 会员开始日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date startdate;
    /**
     * 会员结束日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date enddate;
    /**
     * 状态，启用 1 禁用 0
     */
    private Integer flag;
    /**
     * 订单号
     */
    private String no;
}
