package com.itany.mapper;

import com.itany.dto.ExamineDTO;
import com.itany.dto.ServerAreaDTO;
import com.itany.dto.ServerTypeDTO;
import com.itany.input.ExamineInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ExamineMapper.
 *
 * @author Thou
 * @date 2022/10/22
 */
public interface ExamineMapper {

    /**
     * 根据条件查询 Company Examines
     *
     * @param in -
     * @return java.util.List<com.itany.dto.ExamineDTO>
     * @author Thou
     * @date 2022/10/22
     */
    List<ExamineDTO> listCompanyExamineByParams(ExamineInput in);

    /**
     * 根据条件查询 Server Examines
     *
     * @param in -
     * @return java.util.List<com.itany.dto.ExamineDTO>
     * @author Thou
     * @date 2022/10/22
     */
    List<ExamineDTO> listServerExamineByParams(ExamineInput in);

    /**
     * 根据 id 查询 Company Examine
     *
     * @param id -
     * @return com.itany.dto.ExamineDTO
     * @author Thou
     * @date 2022/10/23
     */
    ExamineDTO getCompanyExamineById(@Param("id") Integer id);

    /**
     * 根据 id 查询 Company Examine
     *
     * @param id -
     * @return com.itany.dto.ExamineDTO
     * @author Thou
     * @date 2022/10/23
     */
    ExamineDTO getServerExamineById(@Param("id") Integer id);

    /**
     * 更新审核状态
     *
     * @param in -
     * @author Thou
     * @date 2022/10/23
     */
    void updateExamineFlag(ExamineInput in);

    /**
     * 批量插入中间表 t_server_type
     *
     * @param list -
     * @author Thou
     * @date 2022/10/23
     */
    void insertServerTypeBatch(@Param("list") List<ServerTypeDTO> list);

    /**
     * 批量插入中间表 t_server_area
     *
     * @param list -
     * @author Thou
     * @date 2022/10/23
     */
    void insertServerAreaBatch(@Param("list") List<ServerAreaDTO> list);
}
