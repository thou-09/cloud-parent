package com.itany.input;

import com.itany.validation.Update;
import com.itany.validation.UpdateFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * UserInput.
 *
 * @author Thou
 * @date 2022/10/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 3148230493745252518L;
    @NotNull(message = "id 不可为空", groups = {UpdateFlag.class})
    private Integer id;
    private String name;
    private String nickname;
    private String phone;
    private String wechat;
    private String qq;
    private String heading;
    private Integer sex;
    @NotNull(message = "状态不可为空", groups = {UpdateFlag.class})
    private Integer flag;
    private Integer companyType;
    private Integer companyid;
}
