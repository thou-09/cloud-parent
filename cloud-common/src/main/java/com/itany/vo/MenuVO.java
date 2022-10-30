package com.itany.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * MenuVO.
 *
 * @author Thou
 * @date 2022/10/30
 */
@Data
public class MenuVO implements Serializable {

    private static final long serialVersionUID = -7384965684063957982L;

    private String menuid;

    private String icon;

    private String menuname;

    private String url;

    private List<MenuVO> menus;
}
