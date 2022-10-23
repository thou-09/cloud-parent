package com.itany.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ServerInfoDTO.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Data
public class ServerInfoDTO implements Serializable {

    private static final long serialVersionUID = -6075668995576267022L;

    private Integer id;

    private String servername;

    private String info;

    private String linkman;

    private String phone;

    private Integer userid;

    private Integer commpanyid;

    private Date createdate;

    private Integer flag;

    private BigDecimal price;
}
