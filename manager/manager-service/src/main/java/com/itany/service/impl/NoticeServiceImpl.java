package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.NoticeDTO;
import com.itany.dto.ServerInfoDTO;
import com.itany.exception.AppException;
import com.itany.input.NoticeInput;
import com.itany.mapper.NoticeMapper;
import com.itany.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * NoticeServiceImpl.
 *
 * @author Thou
 * @date 2022/10/25
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Map<String, Object> listAll(NoticeInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<NoticeDTO> dtos = noticeMapper.listAll();
        PageInfo<NoticeDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public void deleteNoticeById(NoticeInput in) {
        Optional.ofNullable(noticeMapper.getNoticeById(in.getId()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.NOTICE_NOT_EXIST));
        in.setFlag(AppConsts.STATUS_DISABLE);
        noticeMapper.updateFlag(in);
    }

    @Override
    public void insertNoticeForAll(NoticeInput in) {
        in.setManageruserid(1);
        in.setCreatedate(new Date());
        in.setFlag(AppConsts.STATUS_ENABLE);
        noticeMapper.insertNotice(in);
    }
}
