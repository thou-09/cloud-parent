package com.itany.service;

import com.itany.dto.ServerInfoDTO;
import com.itany.input.ServerInfoInput;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * ServerInfoService.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface ServerInfoService {

    /**
     * 根据条件查询 ServerInfo
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/23
     */
    Map<String, Object> listAllByParams(ServerInfoInput in);

    /**
     * 根据 id 查询 ServerInfo
     *
     * @param id -
     * @return com.itany.dto.ServerInfoDTO
     * @author Thou
     * @date 2022/10/24
     */
    ServerInfoDTO getServerInfoById(Integer id);

    /**
     * 根据 id 修改 ServerInfo
     *
     * @param in -
     * @param multipartFiles -
     * @author Thou
     * @date 2022/10/24
     */
    void updateServerInfoById(ServerInfoInput in, MultipartFile[] multipartFiles);
}
