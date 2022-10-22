package com.itany.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * UserVO.
 *
 * @author Thou
 * @date 2022/10/19
 */
@Data
public class UserVO implements Serializable {

    private Integer id;
    private String name;
    private String nickname;
    private String phone;
    private String wechat;
    private String qq;
    private String heading;
    private Integer sex;
    private Integer companyid;
    private Integer flag;
}
