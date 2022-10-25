package com.itany.service;

import com.itany.input.NoticeInput;

import java.util.Map;

/**
 * NoticeService.
 *
 * @author Thou
 * @date 2022/10/25
 */
public interface NoticeService {

    /**
     * 查询所有 Notice
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/25
     */
    Map<String, Object> listAll(NoticeInput in);

    /**
     * 根据 id 删除 Notice
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void deleteNoticeById(NoticeInput in);

    /**
     * 新增全体 Notice
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void insertNoticeForAll(NoticeInput in);
}
