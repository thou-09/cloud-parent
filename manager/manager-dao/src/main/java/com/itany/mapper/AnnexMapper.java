package com.itany.mapper;

import com.itany.entity.Annex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AnnexMapper.
 *
 * @author Thou
 * @date 2022/10/22
 */
public interface AnnexMapper {

    /**
     * 批量插入 Annex
     *
     * @param annexes -
     * @author Thou
     * @date 2022/10/22
     */
    void insertAnnexes(@Param("annexes") List<Annex> annexes);

    /**
     * 根据条件删除 Annex
     *
     * @param annex -
     * @author Thou
     * @date 2022/10/22
     */
    void deleteAnnexByParams(Annex annex);
}
