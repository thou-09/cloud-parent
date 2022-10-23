package com.itany.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.AreaDTO;
import com.itany.dto.ExamineDTO;
import com.itany.dto.ServerAreaDTO;
import com.itany.dto.ServerTypeDTO;
import com.itany.dto.TypeDTO;
import com.itany.entity.Annex;
import com.itany.exception.AppException;
import com.itany.input.ExamineInput;
import com.itany.input.NoticeInput;
import com.itany.input.ServerCommpanyInput;
import com.itany.input.ServerInfoInput;
import com.itany.input.TypeInput;
import com.itany.input.UserInput;
import com.itany.mapper.AnnexMapper;
import com.itany.mapper.AreaMapper;
import com.itany.mapper.ExamineMapper;
import com.itany.mapper.NoticeMapper;
import com.itany.mapper.ServerCommpanyMapper;
import com.itany.mapper.ServerInfoMapper;
import com.itany.mapper.TypeMapper;
import com.itany.mapper.UserMapper;
import com.itany.service.ExamineService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ExamineServiceImpl.
 *
 * @author Thou
 * @date 2022/10/22
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    private ExamineMapper examineMapper;
    @Autowired
    private ServerCommpanyMapper serverCommpanyMapper;
    @Autowired
    private AnnexMapper annexMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ServerInfoMapper serverInfoMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> listCompanyExaminesByParams(ExamineInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<ExamineDTO> dtos = examineMapper.listCompanyExamineByParams(in);
        PageInfo<ExamineDTO> info = new PageInfo<>(dtos);

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ExamineDTO getCompanyExamineById(Integer id) {
        ExamineDTO dto = Optional.ofNullable(examineMapper.getCompanyExamineById(id))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.EXAMINE_NOT_EXIST));
        List<Annex> annexes = dto.getAnnexes().stream()
                .sorted(Comparator.comparing(Annex::getType))
                .collect(Collectors.toList());
        dto.setAnnexes(annexes);
        return dto;
    }

    @Override
    public void examineCompanyExamine(ExamineInput in) {
        ExamineDTO dto = Optional.ofNullable(examineMapper.getCompanyExamineById(in.getId()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.EXAMINE_NOT_EXIST));

        Integer dtoFlag = dto.getFlag();
        if (!AppConsts.EXAMINE_FLAG_WAIT.equals(dtoFlag)) {
            throw new AppException(AppExceptionMsgEnum.DATA_HAS_EXAMINED);
        }

        Integer flag = in.getFlag();
        if (AppConsts.EXAMINE_FLAG_FAIL.equals(flag)) {
            ExamineInput ei = new ExamineInput();
            ei.setId(in.getId());
            ei.setFlag(AppConsts.EXAMINE_FLAG_FAIL);
            ei.setExamineInfo(in.getExamineInfo());
            examineMapper.updateExamineFlag(ei);
        }
        if (AppConsts.EXAMINE_FLAG_SUCCESS.equals(flag)) {
            // 1. 修改状态
            ExamineInput ei = new ExamineInput();
            ei.setId(in.getId());
            ei.setFlag(AppConsts.EXAMINE_FLAG_SUCCESS);
            ei.setExamineInfo(in.getExamineInfo());
            examineMapper.updateExamineFlag(ei);

            // 2. 复制数据到服务商表
            ServerCommpanyInput sci = new ServerCommpanyInput();
            sci.setName(dto.getName());
            sci.setInfo(dto.getInfo());
            sci.setAddress(dto.getAddress());
            sci.setGps(dto.getGps());
            sci.setScale(dto.getScale());
            sci.setCreatedate(dto.getCommpanycreatedate());
            sci.setJoindate(new Date());
            sci.setFlag(AppConsts.STATUS_ENABLE);
            sci.setLinkman(dto.getLinkman());
            sci.setPhone(dto.getPhone());
            sci.setType(dto.getTypeid());
            serverCommpanyMapper.insertServerCommpany(sci);

            // 3. 增加附件数据
            List<Annex> annexes = dto.getAnnexes();
            if (!annexes.isEmpty()) {
                annexes.forEach(annex -> {
                    annex.setId(null);
                    annex.setKeyid(sci.getId());
                    annex.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER_COMMPANY);
                });
                annexMapper.insertAnnexes(annexes);
            }

            // 4. 关联用户
            UserInput ui = new UserInput();
            ui.setId(dto.getUserid());
            ui.setCompanyid(sci.getId());
            userMapper.updateUserById(ui);
        }

        // 5. 发站内通知 TODO
        NoticeInput ni = new NoticeInput();
        ni.setTitle(dto.getName() + "审核情况");
        ni.setInfo(in.getExamineInfo());
        ni.setManageruserid(1);
        ni.setUserid(dto.getUserid());
        ni.setFlag(AppConsts.STATUS_ENABLE);
        ni.setCreatedate(new Date());
        noticeMapper.insertNotice(ni);

        // 6. 在线则通知提示 TODO
    }

    @Override
    public Map<String, Object> listServerExaminesByParams(ExamineInput in) {
        PageHelper.startPage(in.getPage(), in.getRows());
        List<ExamineDTO> dtos = examineMapper.listServerExamineByParams(in);
        PageInfo<ExamineDTO> info = new PageInfo<>(dtos);

        dtos.forEach(examineDTO -> {
            List<String> serverAreaIds = Arrays.asList(examineDTO.getServerarea().split(","));
            List<String> areaNames = areaMapper.listAreasByIds(serverAreaIds)
                    .stream()
                    .map(AreaDTO::getName)
                    .collect(Collectors.toList());
            examineDTO.setServerarea(StringUtils.strip(areaNames.toString(), "[]"));

            List<String> serverTypeIds = Arrays.asList(examineDTO.getServertype().split(","));
            List<String> typeNames = typeMapper.listTypesByIds(serverTypeIds)
                    .stream()
                    .map(TypeDTO::getName)
                    .collect(Collectors.toList());
            examineDTO.setServertype(StringUtils.strip(typeNames.toString(), "[]"));
        });

        Map<String, Object> map = new HashMap<>(4);
        map.put("total", info.getTotal());
        map.put("rows", dtos);
        return map;
    }

    @Override
    public ExamineDTO getServerExamineById(Integer id) {
        ExamineDTO dto = Optional.ofNullable(examineMapper.getServerExamineById(id))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.EXAMINE_NOT_EXIST));

        List<String> serverAreaIds = Arrays.asList(dto.getServerarea().split(","));
        List<String> areaNames = areaMapper.listAreasByIds(serverAreaIds)
                .stream()
                .map(AreaDTO::getName)
                .collect(Collectors.toList());
        dto.setServerarea(StringUtils.strip(areaNames.toString(), "[]"));

        List<String> serverTypeIds = Arrays.asList(dto.getServertype().split(","));
        List<String> typeNames = typeMapper.listTypesByIds(serverTypeIds)
                .stream()
                .map(TypeDTO::getName)
                .collect(Collectors.toList());
        dto.setServertype(StringUtils.strip(typeNames.toString(), "[]"));
        return dto;
    }

    @Override
    public void examineServerExamine(ExamineInput in) {
        ExamineDTO dto = Optional.ofNullable(examineMapper.getServerExamineById(in.getId()))
                .orElseThrow(() -> new AppException(AppExceptionMsgEnum.EXAMINE_NOT_EXIST));

        Integer dtoFlag = dto.getFlag();
        if (!AppConsts.EXAMINE_FLAG_WAIT.equals(dtoFlag)) {
            throw new AppException(AppExceptionMsgEnum.DATA_HAS_EXAMINED);
        }

        Integer flag = in.getFlag();
        if (AppConsts.EXAMINE_FLAG_FAIL.equals(flag)) {
            ExamineInput ei = new ExamineInput();
            ei.setId(in.getId());
            ei.setFlag(AppConsts.EXAMINE_FLAG_FAIL);
            ei.setExamineInfo(in.getExamineInfo());
            examineMapper.updateExamineFlag(ei);
        }
        if (AppConsts.EXAMINE_FLAG_SUCCESS.equals(flag)) {
            // 1. 修改状态
            ExamineInput ei = new ExamineInput();
            ei.setId(in.getId());
            ei.setFlag(AppConsts.EXAMINE_FLAG_SUCCESS);
            ei.setExamineInfo(in.getExamineInfo());
            examineMapper.updateExamineFlag(ei);

            // 2. 复制数据到服务表
            ServerInfoInput sii = new ServerInfoInput();
            sii.setServername(dto.getName());
            sii.setInfo(dto.getInfo());
            sii.setLinkman(dto.getLinkman());
            sii.setPhone(dto.getPhone());
            sii.setUserid(dto.getUserid());
            sii.setCommpanyid(dto.getCommpanyid());
            sii.setCreatedate(new Date());
            sii.setFlag(AppConsts.STATUS_ENABLE);
            sii.setPrice(dto.getPrice());
            serverInfoMapper.insertServerInfo(sii);

            // 3. 增加附件数据
            List<Annex> annexes = dto.getAnnexes();
            if (!annexes.isEmpty()) {
                annexes.forEach(annex -> {
                    annex.setId(null);
                    annex.setKeyid(sii.getId());
                    annex.setTabletype(AppConsts.ANNEX_TABLETYPE_SERVER);
                });
                annexMapper.insertAnnexes(annexes);
            }

            // 4. 关联 Type and Area
            List<String> types = Arrays.asList(dto.getServertype().split(","));
            List<ServerTypeDTO> list1 = new ArrayList<>();
            types.forEach(s -> {
                ServerTypeDTO stDto = new ServerTypeDTO();
                stDto.setTypeid(Integer.parseInt(s));
                stDto.setServerid(sii.getId());
                list1.add(stDto);
            });
            examineMapper.insertServerTypeBatch(list1);
            List<String> areas = Arrays.asList(dto.getServerarea().split(","));
            List<ServerAreaDTO> list2 = new ArrayList<>();
            areas.forEach(s -> {
                ServerAreaDTO saDto = new ServerAreaDTO();
                saDto.setAreaid(Integer.parseInt(s));
                saDto.setServerid(sii.getId());
                list2.add(saDto);
            });
            examineMapper.insertServerAreaBatch(list2);
        }

        // 5. 发站内通知 TODO
        NoticeInput ni = new NoticeInput();
        ni.setTitle(dto.getName() + "审核情况");
        ni.setInfo(in.getExamineInfo());
        ni.setManageruserid(1);
        ni.setUserid(dto.getUserid());
        ni.setFlag(AppConsts.STATUS_ENABLE);
        ni.setCreatedate(new Date());
        noticeMapper.insertNotice(ni);

        // 6. 在线则通知提示 TODO
    }
}
