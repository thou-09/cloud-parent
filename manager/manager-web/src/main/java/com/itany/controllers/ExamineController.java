package com.itany.controllers;

import com.itany.dto.ExamineDTO;
import com.itany.input.ExamineInput;
import com.itany.service.ExamineService;
import com.itany.validation.UpdateFlag;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ExamineController.
 *
 * @author Thou
 * @date 2022/10/22
 */
@RestController
@RequestMapping("/examines")
public class ExamineController {

    @Autowired
    private ExamineService examineService;

    @PostMapping("/company/list")
    public Map<String, Object> listCompanyExamines(ExamineInput in) {
        return examineService.listCompanyExaminesByParams(in);
    }

    @PostMapping("/server/list")
    public Map<String, Object> listServerExamines(ExamineInput in) {
        return examineService.listServerExaminesByParams(in);
    }

    @PostMapping("/company/{id}")
    public ResponseResult<ExamineDTO> findCompanyExamine(@PathVariable Integer id) {
        return ResponseResult.success(examineService.getCompanyExamineById(id));
    }

    @PostMapping("/server/{id}")
    public ResponseResult<ExamineDTO> findServerExamine(@PathVariable Integer id) {
        return ResponseResult.success(examineService.getServerExamineById(id));
    }

    @PostMapping("/company/examine")
    public ResponseResult<Object> examineCompanyExamine(@Validated({UpdateFlag.class}) ExamineInput in) {
        examineService.examineCompanyExamine(in);
        return ResponseResult.success();
    }

    @PostMapping("/server/examine")
    public ResponseResult<Object> examineServerExamine(@Validated({UpdateFlag.class}) ExamineInput in) {
        examineService.examineServerExamine(in);
        return ResponseResult.success();
    }
}
