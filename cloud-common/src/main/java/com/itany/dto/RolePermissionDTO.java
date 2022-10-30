package com.itany.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * RolePermissionDTO.
 *
 * @author Thou
 * @date 2022/10/29
 */
@Data
public class RolePermissionDTO implements Serializable {

    private static final long serialVersionUID = -6436447276504504412L;

    private Integer id;

    private Integer roleid;

    private Integer permissionid;
}
