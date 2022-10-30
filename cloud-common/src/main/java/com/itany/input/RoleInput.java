package com.itany.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * RoleInput.
 *
 * @author Thou
 * @date 2022/10/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 8062858038005316748L;

    private Integer id;

    private String name;

    private List<Integer> permissionIds;
}
