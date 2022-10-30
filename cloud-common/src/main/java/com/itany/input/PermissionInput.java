package com.itany.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * PermissionInput.
 *
 * @author Thou
 * @date 2022/10/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 8292131261032999525L;

    private Integer id;

    private String name;

    private Integer parentid;

    private String url;

    private String parentname;
}
