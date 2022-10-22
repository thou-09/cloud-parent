package com.itany.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import com.itany.entity.Annex;
import com.itany.entity.Member;
import com.itany.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ServerCommpanyDTO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class ServerCommpanyDTO implements Serializable {

    private static final long serialVersionUID = 386303721433133250L;
    private Integer id;
    private String name;
    private String info;
    private String address;
    private String gps;
    private String scale;
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;
    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date joindate;
    private Integer flag;
    private Integer type;
    private String linkman;
    private String phone;
    private Member member;
    private List<User> zzhs;
    private Integer zzhLength;
    private List<Annex> annexes;
}
