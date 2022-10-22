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
 * Notice.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
@TableName("t_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 4707053865260301188L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 通知内容
     */
    private String info;
    /**
     * 发布人员主键
     */
    private Integer manageruserid;
    /**
     * 要通知到的前台用户主键，若是全体通知则为 null
     */
    private Integer userid;
    /**
     * 状态，启用 1 禁用 0
     */
    private Integer flag;
    /**
     * 发送日期
     */
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
}
