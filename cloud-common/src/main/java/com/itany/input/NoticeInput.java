package com.itany.input;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itany.constants.AppConsts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * NoticeInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 4707053865260301188L;

    private Integer id;

    private String title;

    private String info;

    private Integer manageruserid;

    private Integer userid;

    private Integer flag;

    private Date createdate;
}
