package com.itany.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TypeDTO.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Data
public class TypeDTO implements Serializable {

    private static final long serialVersionUID = 1916322468887412998L;

    private Integer id;

    private String name;

    private Integer parentid;

    private Integer type;

    private String parentname;
}
