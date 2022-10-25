package com.itany.controllers;

import com.itany.input.TypeInput;
import com.itany.service.TypeService;
import com.itany.validation.Insert;
import com.itany.validation.Update;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TypeController.
 *
 * @author Thou
 * @date 2022/10/23
 */
@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    public TypeService typeService;

    @PostMapping("/list")
    public Map<String, Object> listTypes(TypeInput in) {
        return typeService.listAllByParams(in);
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyType(@Validated({Update.class}) TypeInput in) {
        typeService.updateTypeById(in);
        return ResponseResult.success();
    }

    @PostMapping("/add")
    public ResponseResult<Object> addType(@Validated({Insert.class}) TypeInput in) {
        typeService.insertType(in);
        return ResponseResult.success();
    }

    @PostMapping("/delete")
    public ResponseResult<Object> deleteType(TypeInput in) {
        typeService.deleteTypeById(in);
        return ResponseResult.success();
    }
}
