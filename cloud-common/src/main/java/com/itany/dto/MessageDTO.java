package com.itany.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MessageDTO.
 *
 * @author Thou
 * @date 2022/10/19
 */
@Data
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 140934990514194042L;

    private Integer id;

    private String name;

    private String msg;

    private String phone;

    private String area;

    private Integer commpanyid;

    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;

    private ServerCommpanyDTO serverCommpanyDTO;
}
