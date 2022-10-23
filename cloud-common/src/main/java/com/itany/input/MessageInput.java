package com.itany.input;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * MessageInput.
 *
 * @author Thou
 * @date 2022/10/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 140934990514194042L;

    private Integer id;

    private String name;

    private String msg;

    private String phone;

    private String area;

    private Integer commpanyid;

    private Date createdate;
}
