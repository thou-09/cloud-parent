package com.itany.mapper;

import com.itany.dto.ServerCommpanyDTO;
import com.itany.input.ServerCommpanyInput;
import com.itany.vo.ServerCommpanyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ServerCommpanyMapper.
 *
 * @author Thou
 * @date 2022/10/20
 */
public interface ServerCommpanyMapper {

    /**
     * 根据条件查询 ServerCommpany
     *
     * @param in -
     * @return java.util.List<com.itany.dto.ServerCommpanyDTO>
     * @author Thou
     * @date 2022/10/20
     */
    List<ServerCommpanyDTO> listAllByParams(ServerCommpanyInput in);

    /**
     * 根据 id 修改 ServerCommpany
     *
     * @param in -
     * @author Thou
     * @date 2022/10/21
     */
    void updateServerCommpanyById(ServerCommpanyInput in);

    /**
     * 根据 id 查询 ServerCommpany
     *
     * @param id -
     * @return com.itany.dto.ServerCommpanyDTO
     * @author Thou
     * @date 2022/10/21
     */
    ServerCommpanyDTO getServerCommpanyById(@Param("id") Integer id);

    /**
     * 添加 ServerCommpany
     *
     * @param in -
     * @author Thou
     * @date 2022/10/23
     */
    void insertServerCommpany(ServerCommpanyInput in);

    /**
     * 只查询 ServerCommpany 不包含其他信息
     *
     * @return java.util.List<com.itany.vo.ServerCommpanyVO>
     * @author Thou
     * @date 2022/10/29
     */
    List<ServerCommpanyVO> listServerCommpanySimple();
}
