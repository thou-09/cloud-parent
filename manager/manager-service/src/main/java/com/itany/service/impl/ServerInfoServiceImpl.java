package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.NoticeDTO;
import com.itany.dto.ServerAreaDTO;
import com.itany.dto.ServerInfoDTO;
import com.itany.dto.ServerTypeDTO;
import com.itany.entity.Annex;
import com.itany.exception.AppException;
import com.itany.input.NoticeInput;
import com.itany.input.ServerInfoInput;
import com.itany.mapper.AnnexMapper;
import com.itany.mapper.NoticeMapper;
import com.itany.mapper.ServerInfoMapper;
import com.itany.service.ServerInfoService;
import com.itany.util.AliyunOssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ServerInfoServiceImpl.
 *
 * @author Thou
 * @date 2022/10/23
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ServerInfoServiceImpl implements ServerInfoService {

    @Autowired
    private ServerInfoMapper serverInfoMapper;
    @Autowired
    private AnnexMapper annexMapper;
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Map<String, Object> listAllByParams(ServerInfoInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<ServerInfoDTO> dtos = serverInfoMapper.listAllByParams(in);
        PageInfo<ServerInfoDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public ServerInfoDTO getServerInfoById(Integer id) {
        return Optional.ofNullable(serverInfoMapper.getServerInfoById(id))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.DATE_PARSE_ERROR));
    }

    @Override
    public void updateServerInfoById(ServerInfoInput in, MultipartFile[] multipartFiles) {
        serverInfoMapper.updateServerInfoById(in);

        Integer flag = in.getFlag();
        if (null != flag) {
            NoticeInput noticeInput = new NoticeInput();
            noticeInput.setManageruserid(1);
            noticeInput.setUserid(in.getUserid());
            noticeInput.setCreatedate(new Date());
            noticeInput.setFlag(AppConsts.STATUS_ENABLE);
            if (AppConsts.STATUS_ENABLE.equals(flag)) {
                noticeInput.setTitle("服务上线通知");
                noticeInput.setInfo(in.getServername() + "已被上线");
            }
            if (AppConsts.STATUS_DISABLE.equals(flag)) {
                noticeInput.setTitle("服务下线通知");
                noticeInput.setInfo(in.getServername() + "已被下线");
            }
            noticeMapper.insertNotice(noticeInput);
        } else {
            List<Integer> servertypeids = in.getServertypeids();
            if (null != servertypeids) {
                serverInfoMapper.deleteServerTypeByServerid(in.getId());
                List<ServerTypeDTO> l1 = new ArrayList<>();
                servertypeids.forEach(id -> {
                    ServerTypeDTO st = new ServerTypeDTO();
                    st.setServerid(in.getId());
                    st.setTypeid(id);
                    l1.add(st);
                });
                serverInfoMapper.insertServerTypeBatch(l1);
            }

            List<Integer> serverareaids = in.getServerareaids();
            if (null != serverareaids) {
                serverInfoMapper.deleteServerAreaByServerid(in.getId());
                List<ServerAreaDTO> l2 = new ArrayList<>();
                serverareaids.forEach(id -> {
                    ServerAreaDTO sa = new ServerAreaDTO();
                    sa.setServerid(in.getId());
                    sa.setAreaid(id);
                    l2.add(sa);
                });
                serverInfoMapper.insertServerAreaBatch(l2);
            }

            List<Annex> annexes = new ArrayList<>();
            if (null != multipartFiles && multipartFiles.length > 0) {
                for (MultipartFile file : multipartFiles) {
                    String url = AliyunOssUtils.ossUploadFile(file);
                    Annex annex = new Annex();
                    annex.setPath(url);
                    annex.setFilename(file.getOriginalFilename());
                    annex.setType(AppConsts.ANNEX_TYPE_SERVER_IMAGE);
                    annex.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER);
                    annex.setKeyid(in.getId());
                    annexes.add(annex);
                }
                Annex remove = new Annex();
                remove.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER);
                remove.setKeyid(in.getId());
                remove.setType(AppConsts.ANNEX_TYPE_SERVER_IMAGE);
                annexMapper.deleteAnnexByParams(remove);
                annexMapper.insertAnnexes(annexes);
            }

            NoticeInput noticeInput = new NoticeInput();
            noticeInput.setManageruserid(1);
            noticeInput.setUserid(in.getUserid());
            noticeInput.setCreatedate(new Date());
            noticeInput.setFlag(AppConsts.STATUS_ENABLE);
            noticeInput.setTitle("服务修改通知");
            noticeInput.setInfo(in.getServername() + "已被修改");
            noticeMapper.insertNotice(noticeInput);
        }
    }
}
