package com.itany.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * NoticeDTO.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Data
public class NoticeDTO implements Serializable {

    private static final long serialVersionUID = 4707053865260301188L;

    private Integer id;

    private String title;

    private String info;

    private Integer manageruserid;

    private ManagerUserDTO managerUserDTO;

    private Integer userid;

    private UserDTO userDTO;

    private Integer flag;

    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
}
