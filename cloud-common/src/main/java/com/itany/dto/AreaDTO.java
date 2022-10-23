package com.itany.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * AreaDTO.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Data
public class AreaDTO implements Serializable {

    private static final long serialVersionUID = 3982857099283851746L;

    private Integer id;

    private String name;

    private Integer parentid;
}
