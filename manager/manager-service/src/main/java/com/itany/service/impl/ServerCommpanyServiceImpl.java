package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.ServerCommpanyDTO;
import com.itany.entity.Annex;
import com.itany.exception.AppException;
import com.itany.input.ServerCommpanyInput;
import com.itany.mapper.AnnexMapper;
import com.itany.mapper.ServerCommpanyMapper;
import com.itany.service.ServerCommpanyService;
import com.itany.util.AliyunOssUtils;
import com.itany.vo.ServerCommpanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ServerCommpanyServiceImpl.
 *
 * @author Thou
 * @date 2022/10/20
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ServerCommpanyServiceImpl implements ServerCommpanyService {

    @Autowired
    private ServerCommpanyMapper serverCommpanyMapper;
    @Autowired
    private AnnexMapper annexMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object>  listAllByParams(ServerCommpanyInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<ServerCommpanyDTO> dtos = serverCommpanyMapper.listAllByParams(in);
        PageInfo<ServerCommpanyDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public void updateServerCommpanyById(ServerCommpanyInput in, MultipartFile[] multipartFiles, MultipartFile mainFile) {
        String typeStr = in.getTypeStr();
        if (null != typeStr && !"".equals(typeStr)) {
            if ("生活".equals(typeStr)) {
                in.setType(AppConsts.TYPE_TYPE_LIFE);
            }
            if ("商务".equals(typeStr)) {
                in.setType(AppConsts.TYPE_TYPE_BUSINESS);
            }
        }

        serverCommpanyMapper.updateServerCommpanyById(in);

        List<Annex> annexes = new ArrayList<>();
        if (null != mainFile) {
            String url = AliyunOssUtils.ossUploadFile(mainFile);
            Annex annex = new Annex();
            annex.setPath(url);
            annex.setFilename(mainFile.getOriginalFilename());
            annex.setType(AppConsts.ANNEX_TYPE_SERVER_COMMPANY_LICENSE_IMAGE);
            annex.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER_COMMPANY);
            annex.setKeyid(in.getId());
            annexes.add(annex);

            Annex remove = new Annex();
            remove.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER_COMMPANY);
            remove.setKeyid(in.getId());
            remove.setType(AppConsts.ANNEX_TYPE_SERVER_COMMPANY_LICENSE_IMAGE);
            annexMapper.deleteAnnexByParams(remove);
        }

        if (null != multipartFiles && multipartFiles.length > 0) {
            for (MultipartFile file : multipartFiles) {
                String url = AliyunOssUtils.ossUploadFile(file);
                Annex annex = new Annex();
                annex.setPath(url);
                annex.setFilename(file.getOriginalFilename());
                annex.setType(AppConsts.ANNEX_TYPE_SERVER_COMMPANY_IMAGE);
                annex.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER_COMMPANY);
                annex.setKeyid(in.getId());
                annexes.add(annex);
            }
            Annex remove = new Annex();
            remove.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER_COMMPANY);
            remove.setKeyid(in.getId());
            remove.setType(AppConsts.ANNEX_TYPE_SERVER_COMMPANY_IMAGE);
            annexMapper.deleteAnnexByParams(remove);
        }

        if (annexes.size() != 0) {
            annexMapper.insertAnnexes(annexes);
        }
    }

    @Override
    public ServerCommpanyDTO getServerCommpanyById(Integer id) {
        ServerCommpanyDTO dto = Optional.ofNullable(serverCommpanyMapper.getServerCommpanyById(id))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.SERVER_COMPANY_NOT_EXIST));
        List<Annex> annexes = dto.getAnnexes().stream()
                .sorted(Comparator.comparing(Annex::getType))
                .collect(Collectors.toList());
        dto.setAnnexes(annexes);
        return dto;
    }

    @Override
    public List<ServerCommpanyVO> listServerCommpanySimple() {
        return serverCommpanyMapper.listServerCommpanySimple();
    }
}
