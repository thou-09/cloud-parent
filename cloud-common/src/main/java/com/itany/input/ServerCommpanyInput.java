package com.itany.input;

import com.itany.validation.Update;
import com.itany.validation.UpdateFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * ServerCommpanyInput.
 *
 * @author Thou
 * @date 2022/10/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerCommpanyInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 386303721433133250L;

    @NotNull(message = "id 不可为空", groups = {UpdateFlag.class, Update.class})
    private Integer id;

    @NotBlank(message = "服务商名称不可为空", groups = {Update.class})
    private String name;

    @Length(max = 300, message = "服务商详情不可超过300字符", groups = {Update.class})
    private String info;

    @NotBlank(message = "公司地址不可为空", groups = {Update.class})
    private String address;

    @NotBlank(message = "gps 坐标不可为空", groups = {Update.class})
    private String gps;

    @NotBlank(message = "规模不可为空", groups = {Update.class})
    private String scale;

    @NotNull(message = "注册时间格式必须为 yyyy-MM-dd", groups = {Update.class})
    private Date createdate;

    private Date joindate;

    private Integer flag;

    private Integer type;

    @Pattern(regexp = "^生活$|^商务$", message = "服务商类型只允许填写(生活)或(商务)", groups = {Update.class})
    private String typeStr;

    @NotBlank(message = "联系人不可为空", groups = {Update.class})
    private String linkman;

    @NotBlank(message = "公司电话不可为空", groups = {Update.class})
    private String phone;

    private Integer memberId;
}
