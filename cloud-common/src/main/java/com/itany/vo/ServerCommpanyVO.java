package com.itany.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * ServerCommpanyVO.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class ServerCommpanyVO implements Serializable {

    private static final long serialVersionUID = 386303721433133250L;

    private Integer id;

    private String text;
}
