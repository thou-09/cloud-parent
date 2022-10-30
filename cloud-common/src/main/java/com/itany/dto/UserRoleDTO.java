package com.itany.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * UserRoleDTO.
 *
 * @author Thou
 * @date 2022/10/29
 */
@Data
public class UserRoleDTO implements Serializable {

    private static final long serialVersionUID = 4359181376193049182L;

    private Integer userid;

    private Integer roleid;
}
