package com.itany.controllers;

import com.itany.dto.ServerCommpanyDTO;
import com.itany.input.ServerCommpanyInput;
import com.itany.service.ServerCommpanyService;
import com.itany.validation.Update;
import com.itany.validation.UpdateFlag;
import com.itany.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * ServerCommpanyController.
 *
 * @author Thou
 * @date 2022/10/20
 */
@RestController
@RequestMapping("/serverCompanies")
public class ServerCommpanyController {

    @Autowired
    private ServerCommpanyService serverCommpanyService;

    @PostMapping("/companylist")
    public Map<String, Object> listServerCommpanys(ServerCommpanyInput in) {
        return serverCommpanyService.listAllByParams(in);
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyServerCommpany(
            @Validated({Update.class}) ServerCommpanyInput in,
            @RequestParam(value = "files", required = false) MultipartFile[] multipartFiles,
            @RequestParam(value = "mainfile", required = false) MultipartFile mainFile) {
        serverCommpanyService.updateServerCommpanyById(in, multipartFiles, mainFile);
        return ResponseResult.success();
    }

    @PostMapping("/modify/flag")
    public ResponseResult<Object> modifyServerCommpanyFlag(@Validated({UpdateFlag.class}) ServerCommpanyInput in) {
        serverCommpanyService.updateServerCommpanyById(in, null, null);
        return ResponseResult.success();
    }

    @PostMapping("/company/{id}")
    public ResponseResult<ServerCommpanyDTO> findServerCommpany(@PathVariable Integer id) {
        return ResponseResult.success(serverCommpanyService.getServerCommpanyById(id));
    }
}
