package com.itany.dao;

import com.itany.dto.MemberCompanyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * LifeDao.
 *
 * @author Thou
 * @date 2022/11/2
 */
@Repository
public interface LifeDao {

    /**
     * 新增一个会员订单信息
     *
     * @param memberCompanyDTO -
     * @author Thou
     * @date 2022/11/2
     */
    void insertMemberCompany(MemberCompanyDTO memberCompanyDTO);

    /**
     * 修改会员订单状态
     *
     * @param no -
     * @param flag -
     * @author Thou
     * @date 2022/11/2
     */
    void updateMemberCompanyFlag(@Param("no") String no, @Param("flag") Integer flag);

    /**
     * 根据 no 查询 MemberCompany
     *
     * @param no -
     * @return com.itany.dto.MemberCompanyDTO
     * @author Thou
     * @date 2022/11/2
     */
    MemberCompanyDTO getMemberCompanyByNo(@Param("no") String no);

    /**
     * 获取最新的购买记录
     *
     * @return com.itany.dto.MemberCompanyDTO
     * @author Thou
     * @date 2022/11/2
     */
    MemberCompanyDTO getLatest();
}
