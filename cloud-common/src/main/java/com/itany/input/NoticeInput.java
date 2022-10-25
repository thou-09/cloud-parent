package com.itany.input;

import com.itany.validation.Delete;
import com.itany.validation.Insert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * NoticeInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 4707053865260301188L;

    @NotNull(message = "id 不可为空", groups = {Delete.class})
    private Integer id;

    @NotBlank(message = "标题不可为空", groups = {Insert.class})
    private String title;

    @NotBlank(message = "内容不可为空", groups = {Insert.class})
    private String info;

    private Integer manageruserid;

    private Integer userid;

    private Integer flag;

    private Date createdate;
}
