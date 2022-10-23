package com.itany.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.constants.AppConsts;
import com.itany.entity.Annex;
import com.itany.input.BaseInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ExamineDTO.
 *
 * @author Thou
 * @date 2022/10/22
 */
@Data
public class ExamineDTO implements Serializable {

    private static final long serialVersionUID = -203374846690910275L;

    private Integer id;

    private String title;

    private String name;

    private Integer typeid;

    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date createdate;

    private String linkman;

    private String phone;

    private Integer userid;

    @JSONField(format = AppConsts.FORMAT_DATE)
    private Date commpanycreatedate;

    private String address;

    private String scale;

    private String gps;

    private Integer examinetype;

    private Integer flag;

    private String servertype;

    private String serverarea;

    private String info;

    private Integer commpanyid;

    private String commpanyname;

    private String examineInfo;

    private BigDecimal price;

    private List<Annex> annexes;
}
