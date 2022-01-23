package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.entity.param.UserCdResultParam;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.CdInfoMapper;
import com.chufinfo.sorting.model.CdInfo;
import com.chufinfo.sorting.model.RoleCd;
import com.chufinfo.sorting.mapper.primary.RoleCdMapper;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.service.IRoleCdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litk
 * @since 2022-01-19
 */
@Service
@Slf4j
public class RoleCdServiceImpl extends ServiceImpl<RoleCdMapper, RoleCd> implements IRoleCdService {

    @Autowired
    private CdInfoMapper cdInfoMapper;
    @Override
    public List<UserCdResultParam> userRoleCdInfo(User user) {
        EntityWrapper<RoleCd> roleCdEntityWrapper=new EntityWrapper<>();
        roleCdEntityWrapper.eq("level",user.getLevel());
        roleCdEntityWrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        List<RoleCd> roleCdList=this.baseMapper.selectList(roleCdEntityWrapper);
        if(roleCdList==null||roleCdList.size()==0){
            throw new RRException("请联系系统管理员分配菜单权限");
        }
        List<Integer> cdBhList=roleCdList.stream().map(RoleCd::getCdBh).collect(Collectors.toList());
        EntityWrapper<CdInfo> cdInfoEntityWrapper=new EntityWrapper<>();
        cdInfoEntityWrapper.in("cd_bh",cdBhList.toArray());
        cdInfoEntityWrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        List<CdInfo> cdInfoList=cdInfoMapper.selectList(cdInfoEntityWrapper);
        List<UserCdResultParam> userCdResultParamList=new ArrayList<>();
        roleCdList.forEach(roleCd -> {
            UserCdResultParam userCdResultParam=new UserCdResultParam();
            CdInfo cdInfo=cdInfoList.stream().filter(item->item.getCdBh().equals(roleCd.getCdBh())).findFirst().orElse(null);
            userCdResultParam.setId(roleCd.getId());
            if(cdInfo!=null){
                userCdResultParam.setCdBh(cdInfo.getCdBh());
                userCdResultParam.setCdMc(cdInfo.getCdMc());
                userCdResultParam.setCdUrl(cdInfo.getCdUrl());
            }
            userCdResultParamList.add(userCdResultParam);
        });
        return userCdResultParamList;
    }
}
