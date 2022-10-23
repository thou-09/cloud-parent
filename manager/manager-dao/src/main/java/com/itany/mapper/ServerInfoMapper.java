package com.itany.mapper;

import com.itany.dto.ServerInfoDTO;
import com.itany.input.ServerInfoInput;

import java.util.List;

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

    /**
     * 根据条件查询 ServerInfo
     *
     * @param in -
     * @return java.util.List<com.itany.dto.ServerInfoDTO>
     * @author Thou
     * @date 2022/10/23
     */
    List<ServerInfoDTO> listAllByParams(ServerInfoInput in);
}
