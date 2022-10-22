package com.itany.input;

import com.itany.constants.AppConsts;
import lombok.Data;

import java.io.Serializable;

/**
 * BaseInput.
 *
 * @author Thou
 * @date 2022/10/18
 */
@Data
public class BaseInput implements Serializable {

    private static final long serialVersionUID = 4645897931742340079L;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 页面大小
     */
    private Integer rows;

    public Integer getPage() {
        return page == null ? AppConsts.DEFAULT_PAGE_NO : page;
    }

    public Integer getRows() {
        return rows == null ? AppConsts.DEFAULT_PAGE_SIZE : rows;
    }
}
