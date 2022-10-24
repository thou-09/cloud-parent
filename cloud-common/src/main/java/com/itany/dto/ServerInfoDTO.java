package com.itany.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import com.itany.entity.Annex;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    private String username;

    private Integer commpanyid;

    private String commpanyname;

    private Integer commpanytype;

    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;

    private Integer flag;

    private BigDecimal price;

    private String memberlevel;

    private List<TypeDTO> typeDTOList;

    private List<AreaDTO> areaDTOList;

    private List<Annex> annexList;
}
