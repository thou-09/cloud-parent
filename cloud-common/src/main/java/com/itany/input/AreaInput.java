package com.itany.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * AreaInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AreaInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 3982857099283851746L;

    private Integer id;

    private String name;

    private Integer parentid;
}
