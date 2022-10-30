package com.itany.dto;

import com.itany.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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

    private String companyname;

    private List<Role> roles;
}
