package com.itany.mapper;

import com.itany.dto.NoticeDTO;
import com.itany.input.NoticeInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询全部
     *
     * @return java.util.List<com.itany.dto.NoticeDTO>
     * @author Thou
     * @date 2022/10/25
     */
    List<NoticeDTO> listAll();

    /**
     * 修改 flag
     *
     * @param in -
     * @author Thou
     * @date 2022/10/25
     */
    void updateFlag(NoticeInput in);

    /**
     * 根据 id 查询 Notice
     *
     * @param id -
     * @return com.itany.dto.NoticeDTO
     * @author Thou
     * @date 2022/10/25
     */
    NoticeDTO getNoticeById(@Param("id") Integer id);
}
