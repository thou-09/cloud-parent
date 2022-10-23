package com.itany.service;

import com.itany.dto.ExamineDTO;
import com.itany.input.ExamineInput;

import java.util.Map;

/**
 * ExamineService.
 *
 * @author Thou
 * @date 2022/10/22
 */
public interface ExamineService {

    /**
     * 根据条件查询 Company Examines
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/22
     */
    Map<String, Object> listCompanyExaminesByParams(ExamineInput in);

    /**
     * 根据 id 查询 Company Examine
     *
     * @param id -
     * @return com.itany.dto.ExamineDTO
     * @author Thou
     * @date 2022/10/23
     */
    ExamineDTO getCompanyExamineById(Integer id);

    /**
     * 审核 Company Examine
     *
     * @param in -
     * @author Thou
     * @date 2022/10/23
     */
    void examineCompanyExamine(ExamineInput in);

    /**
     * 根据条件查询 Server Examines
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/22
     */
    Map<String, Object> listServerExaminesByParams(ExamineInput in);

    /**
     * 根据 id 查询 Server Examine
     *
     * @param id -
     * @return com.itany.dto.ExamineDTO
     * @author Thou
     * @date 2022/10/23
     */
    ExamineDTO getServerExamineById(Integer id);

    /**
     * 审核 Server Examine
     *
     * @param in -
     * @author Thou
     * @date 2022/10/23
     */
    void examineServerExamine(ExamineInput in);
}
