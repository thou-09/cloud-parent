package com.itany.controllers;

import com.itany.input.NoticeInput;
import com.itany.service.NoticeService;
import com.itany.validation.Delete;
import com.itany.validation.Insert;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * NoticeController.
 *
 * @author Thou
 * @date 2022/10/25
 */
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/list")
    public Map<String, Object> listAll(NoticeInput in) {
        return noticeService.listAll(in);
    }

    @PostMapping("/add")
    public ResponseResult<Object> addNoticeForAll(@Validated({Insert.class}) NoticeInput in) {
        noticeService.insertNoticeForAll(in);
        return ResponseResult.success();
    }

    @PostMapping("/delete")
    public ResponseResult<Object> deleteNotice(@Validated({Delete.class}) NoticeInput in) {
        noticeService.deleteNoticeById(in);
        return ResponseResult.success();
    }
}
