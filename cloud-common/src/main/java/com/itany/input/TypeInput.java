package com.itany.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TypeInput.
 *
 * @author Thou
 * @date 2022/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TypeInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = 1916322468887412998L;

    private Integer id;

    private String name;

    private Integer parentid;

    private Integer type;
}
