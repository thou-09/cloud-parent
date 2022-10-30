package com.itany.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PermissionVO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class PermissionVO implements Serializable {

    private static final long serialVersionUID = 8292131261032999525L;

    private Integer id;

    private String text;

    private String state;

    private List<PermissionVO> children;
}
