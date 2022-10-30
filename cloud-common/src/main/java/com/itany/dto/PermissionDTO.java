package com.itany.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PermissionDTO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 8292131261032999525L;

    private Integer id;

    private String name;

    private Integer parentid;

    private String url;

    private PermissionDTO parentPermission;

    private List<PermissionDTO> sonsPermission;
}
