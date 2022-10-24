package com.itany.controllers;

import com.itany.dto.ServerInfoDTO;
import com.itany.input.ServerInfoInput;
import com.itany.service.ServerInfoService;
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

import java.util.Map;

/**
 * ServerInfoController.
 *
 * @author Thou
 * @date 2022/10/23
 */
@RestController
@RequestMapping("/servers")
public class ServerInfoController {

    @Autowired
    private ServerInfoService serverInfoService;

    @PostMapping("/list")
    public Map<String, Object> listServers(ServerInfoInput in) {
        return serverInfoService.listAllByParams(in);
    }

    @PostMapping("/server/{id}")
    public ResponseResult<ServerInfoDTO> findServer(@PathVariable Integer id) {
        return ResponseResult.success(serverInfoService.getServerInfoById(id));
    }

    @PostMapping("/modify/flag")
    public ResponseResult<Object> modifyServerFlag(@Validated({UpdateFlag.class}) ServerInfoInput in) {
        serverInfoService.updateServerInfoById(in, null);
        return ResponseResult.success();
    }

    @PostMapping("/modify")
    public ResponseResult<Object> modifyServer(@Validated({Update.class}) ServerInfoInput in,
                                               @RequestParam(value = "files", required = false) MultipartFile[] multipartFiles) {
        serverInfoService.updateServerInfoById(in, multipartFiles);
        return ResponseResult.success();
    }
}
