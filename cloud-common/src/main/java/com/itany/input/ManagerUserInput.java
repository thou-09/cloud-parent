package com.itany.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * ManagerUserInput.
 *
 * @author Thou
 * @date 2022/10/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerUserInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 6632845062716545505L;
    private Integer id;
    @NotBlank(message = "用户名不可为空")
    private String username;
    @NotBlank(message = "密码不可为空")
    private String password;
    private Integer commpanyid;
}
