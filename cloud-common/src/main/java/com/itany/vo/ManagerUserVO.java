package com.itany.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * ManagerUserVO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class ManagerUserVO implements Serializable {

    private static final long serialVersionUID = 6632845062716545505L;
    private Integer id;
    private String username;
    private Integer commpanyid;
}
