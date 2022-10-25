package com.itany.input;

import com.itany.validation.Insert;
import com.itany.validation.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TypeInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TypeInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 1916322468887412998L;

    @NotNull(message = "id 不可为空", groups = {Update.class})
    private Integer id;

    @NotBlank(message = "类型名称不可为空", groups = {Update.class, Insert.class})
    private String name;

    private Integer parentid;

    @NotNull(message = "类型不能为空", groups = {Insert.class})
    private Integer type;
}
