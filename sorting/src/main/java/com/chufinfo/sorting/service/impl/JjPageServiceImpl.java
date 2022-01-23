package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.entity.param.FjSgResultParam;
import com.chufinfo.sorting.entity.param.JjSgjResultParam;
import com.chufinfo.sorting.entity.param.JjXjSgjParam;
import com.chufinfo.sorting.entity.param.YBXXResultParam;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.*;
import com.chufinfo.sorting.model.*;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IFjPageService;
import com.chufinfo.sorting.service.IJjPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litk
 * @since 2022-01-06
 */
@Service
@Slf4j
public class JjPageServiceImpl extends ServiceImpl<JjPageMapper, JjSgjResultParam> implements IJjPageService {
    @Autowired
    private BbmxMapper bbmxMapper;
    @Autowired
    private FjkMapper fjkMapper;
    @Autowired
    private JymdbmMapper jymdbmMapper;
    @Autowired
    private SgjMapper sgjMapper;
    @Autowired
    private SjxxMapper sjxxMapper;
    @Autowired
    private UserFjkMapper userFjkMapper;
    @Override
    public List<JjSgjResultParam> getJjPageSgjList(User user) {
        List<JjSgjResultParam> jjSgjResultParamList=new ArrayList<>();
        List<UserFjk> userFjkList = getUserFjks(user);
        if(userFjkList==null||userFjkList.size()==0){
            return jjSgjResultParamList;
        }
        //用户需要交接的分拣框
        List<Long> userFjkIds=userFjkList.stream().map(UserFjk::getFjkId).collect(Collectors.toList());
        //已下架的待交接试管架（上架信息表）
        List<Sjxx> djjSgj=getSjxxSgj(userFjkIds);
        if(djjSgj==null||djjSgj.size()==0)
            return jjSgjResultParamList;
        djjSgj.forEach(sjxx -> {
            JjSgjResultParam jjSgjResultParam=new JjSgjResultParam();
            jjSgjResultParam.setSgjId(sjxx.getSgjId());
            jjSgjResultParam.setFjkId(sjxx.getFjkId());
            jjSgjResultParam.setJjId(sjxx.getId());//待交接上架id
            jjSgjResultParam.setYbSL(sjxx.getYbsl());
            jjSgjResultParamList.add(jjSgjResultParam);
        });
        //试管加ids
        List<Long> sgjIds=djjSgj.stream().map(Sjxx::getSgjId).collect(Collectors.toList());
        List<Sgj> sgjList=getSgjListByIds(sgjIds);
        jjSgjResultParamList.forEach(jjSgjResultParam -> {
            Sgj sgj=sgjList.stream().filter(item->item.getId().equals(jjSgjResultParam.getSgjId())).findFirst().orElse(null);
            if(sgj!=null){
                jjSgjResultParam.setSgjRl(sgj.getSgjRl());//试管架容量
                jjSgjResultParam.setSgjBh(sgj.getSgjBh());//试管架编号
            }

        });
        return jjSgjResultParamList;
    }

    private List<UserFjk> getUserFjks(User user) {
        EntityWrapper<UserFjk> userFjkEntityWrapper=new EntityWrapper<>();
        userFjkEntityWrapper.eq("user_id", user.getId());
        userFjkEntityWrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        return this.userFjkMapper.selectList(userFjkEntityWrapper);
    }

    @Override
    @Transactional
    public void confirmJJSGJ(User user, List<JjSgjResultParam> jjSgjResultParamList) {
        if (jjSgjResultParamList==null||jjSgjResultParamList.size()==0){
            throw new RRException("交接信息不能为空");
        }
        JjXjSgjParam param=new JjXjSgjParam();
        param.setJjUserId(user.getId());
        param.setJjIds(jjSgjResultParamList.stream().map(JjSgjResultParam::getJjId).collect(Collectors.toList()));
        this.baseMapper.updateBatchSjxx(param);//更具id更新sjxx表 stauts从已下架=1更新为已交接=2交接用户jj_user_id
        this.baseMapper.updateBatchBbmx(param);//根据sj_id更新bbmx stauts从未交接=0更新为已交接=1 交接用户jj_user_id
    }

    /**
     * 从上架信息表获取所有待交接试管架
     * jjxx->status=1;
     * @return
     */
    public List<Sjxx> getSjxxSgj(List<Long> userFjkIds){
        EntityWrapper<Sjxx> wrapper=new EntityWrapper<>();
        wrapper.in("fjk_id",userFjkIds.toArray());
        wrapper.gt("ybsl",0);
        wrapper.eq("status",SystemEnum.sgjStatus.XJSGJ.getCode());
        return  sjxxMapper.selectList(wrapper);

    }

    /**
     * 试管架基本信息
     * @param sgjIds
     * @return
     */
    public List<Sgj> getSgjListByIds(List<Long> sgjIds){

        EntityWrapper<Sgj> wrapper=new EntityWrapper<>();
        wrapper.in("id",sgjIds.toArray());
        return sgjMapper.selectList(wrapper);
    }
}