package com.itany.input;

import com.itany.validation.Update;
import com.itany.validation.UpdateFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ServerInfoInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerInfoInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = -6075668995576267022L;

    @NotNull(message = "id 不可为空", groups = {UpdateFlag.class, Update.class})
    private Integer id;

    @NotBlank(message = "服务名称不可为空", groups = {Update.class})
    private String servername;

    @Length(max = 500, message = "服务详情不可超过500字符", groups = {Update.class})
    private String info;

    @NotBlank(message = "联系人不可为空", groups = {Update.class})
    private String linkman;

    @NotBlank(message = "联系电话不可为空", groups = {Update.class})
    private String phone;

    private Integer userid;

    private Integer commpanyid;

    private Date createdate;

    @NotNull(message = "状态不可为空", groups = {UpdateFlag.class})
    private Integer flag;

    private BigDecimal price;

    private String servertype;

    @NotEmpty(message = "服务范围不可为空", groups = {Update.class})
    private List<Integer> servertypeids;

    private String serverarea;

    @NotEmpty(message = "服务地区不可为空", groups = {Update.class})
    private List<Integer> serverareaids;
}
