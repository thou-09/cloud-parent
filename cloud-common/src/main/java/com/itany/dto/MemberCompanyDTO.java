package com.itany.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MemberCompanyDTO.
 *
 * @author Thou
 * @date 2022/11/2
 */
@Data
public class MemberCompanyDTO implements Serializable {

    private static final long serialVersionUID = -5999888777291037533L;

    private Integer id;

    private Integer memberid;

    private Integer commpanyid;

    private Date startdate;

    private Date enddate;

    private Integer flag;

    private String no;
}
