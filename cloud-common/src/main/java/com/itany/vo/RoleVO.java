package com.itany.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * RoleVO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 8062858038005316748L;

    private Integer id;

    private String text;
}
