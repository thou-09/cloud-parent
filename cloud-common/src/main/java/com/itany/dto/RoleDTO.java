package com.itany.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * RoleDTO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 8062858038005316748L;

    private Integer id;

    private String name;

    private String url;

    private List<PermissionDTO> permissions;
}
