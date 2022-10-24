package com.itany.mapper;

import com.itany.dto.ServerAreaDTO;
import com.itany.dto.ServerInfoDTO;
import com.itany.dto.ServerTypeDTO;
import com.itany.input.ServerInfoInput;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据 id 查询 ServerInfo
     *
     * @param id -
     * @return com.itany.dto.ServerInfoDTO
     * @author Thou
     * @date 2022/10/24
     */
    ServerInfoDTO getServerInfoById(@Param("id") Integer id);

    /**
     * 根据 id 修改 ServerInfo
     *
     * @param in -
     * @author Thou
     * @date 2022/10/21
     */
    void updateServerInfoById(ServerInfoInput in);

    /**
     * 根据 serverid 删除 ServerType
     *
     * @param serverid -
     * @author Thou
     * @date 2022/10/24
     */
    void deleteServerTypeByServerid(@Param("serverid") Integer serverid);

    /**
     * 根据 serverid 删除 ServerArea
     *
     * @param serverid -
     * @author Thou
     * @date 2022/10/24
     */
    void deleteServerAreaByServerid(@Param("serverid") Integer serverid);

    /**
     * 批量添加 ServerType
     *
     * @param list -
     * @author Thou
     * @date 2022/10/24
     */
    void insertServerTypeBatch(List<ServerTypeDTO> list);

    /**
     * 批量添加 ServerArea
     *
     * @param list -
     * @author Thou
     * @date 2022/10/24
     */
    void insertServerAreaBatch(List<ServerAreaDTO> list);
}
