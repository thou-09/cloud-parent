package com.itany.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * ManagerUserDTO.
 *
 * @author Thou
 * @date 2022/10/19
 */
@Data
public class ManagerUserDTO implements Serializable {

    private static final long serialVersionUID = 6632845062716545505L;

    private Integer id;

    private String username;

    // private String password;

    private Integer commpanyid;
}
