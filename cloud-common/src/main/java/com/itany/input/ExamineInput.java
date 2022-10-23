package com.itany.input;

import com.itany.validation.UpdateFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ExamineInput.
 *
 * @author Thou
 * @date 2022/10/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExamineInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = -203374846690910275L;

    @NotNull(message = "id 不可为空", groups = {UpdateFlag.class})
    private Integer id;

    private String title;

    private String name;

    private Integer typeid;

    private Date createdate;

    private String linkman;

    private String phone;

    private Integer userid;

    private Date commpanycreatedate;

    private String address;

    private String scale;

    private String gps;

    private Integer examinetype;

    @NotNull(message = "审核状态不可为空", groups = {UpdateFlag.class})
    private Integer flag;

    private String servertype;

    private String serverarea;

    private String info;

    private Integer commpanyid;

    @NotBlank(message = "审核信息不可为空", groups = {UpdateFlag.class})
    private String examineInfo;

    private BigDecimal price;
}
