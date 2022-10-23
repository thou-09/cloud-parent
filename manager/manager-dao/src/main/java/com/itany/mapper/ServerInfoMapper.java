package com.itany.mapper;

import com.itany.input.ServerInfoInput;

/**
 * ServerInfoMapper.
 *
 * @author Thou
 * @date 2022/10/23
 */
public interface ServerInfoMapper {

    /**
     * 新增一条 ServerInfo
     *
     * @param in -
     * @author Thou
     * @date 2022/10/23
     */
    void insertServerInfo(ServerInfoInput in);
}
