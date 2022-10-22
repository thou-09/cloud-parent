package com.itany.service;

import com.itany.dto.ServerCommpanyDTO;
import com.itany.input.ServerCommpanyInput;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * ServerCommpanyService.
 *
 * @author Thou
 * @date 2022/10/20
 */
public interface ServerCommpanyService {

    /**
     * 根据条件查询 ServerCommpany
     *
     * @param in -
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/10/20
     */
    Map<String, Object> listAllByParams(ServerCommpanyInput in);

    /**
     * 根据 id 修改 ServerCommpany
     *
     * @param in -
     * @param multipartFiles -
     * @param mainFile -
     * @author Thou
     * @date 2022/10/21
     */
    void updateServerCommpanyById(ServerCommpanyInput in, MultipartFile[] multipartFiles, MultipartFile mainFile);

    /**
     * 根据 id 查询 ServerCommpany
     *
     * @param id -
     * @return com.itany.dto.ServerCommpanyDTO
     * @author Thou
     * @date 2022/10/21
     */
    ServerCommpanyDTO getServerCommpanyById(Integer id);
}
