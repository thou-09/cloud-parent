package com.itany.service;

import com.itany.input.ServerInfoInput;

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
}
