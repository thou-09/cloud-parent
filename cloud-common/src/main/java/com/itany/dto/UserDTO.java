package com.itany.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * UserDTO.
 *
 * @author Thou
 * @date 2022/10/19
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 5457443388220824058L;

    private Integer id;

    private String name;

    private String password;

    private String nickname;

    private String phone;

    private String wechat;

    private String qq;

    private String headimg;

    private Integer sex;

    private Integer companyid;

    private Integer flag;

    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;

    private ServerCommpanyDTO serverCommpanyDTO;
}
