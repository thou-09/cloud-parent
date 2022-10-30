package com.itany.input;

import com.itany.validation.Insert;
import com.itany.validation.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

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

    @NotBlank(message = "用户名不可为空", groups = {Update.class, Insert.class})
    private String username;

    @NotBlank(message = "密码不可为空", groups = {Insert.class})
    private String password;

    private Integer commpanyid;

    private List<Integer> roleIds;
}
