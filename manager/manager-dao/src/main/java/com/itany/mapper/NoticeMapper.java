package com.itany.mapper;

import com.itany.input.NoticeInput;

/**
 * NoticeMapper.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface NoticeMapper {

    /**
     * 新增一条 Notice
     *
     * @param in -
     * @author Thou
     * @date 2022/10/23
     */
    void insertNotice(NoticeInput in);
}
