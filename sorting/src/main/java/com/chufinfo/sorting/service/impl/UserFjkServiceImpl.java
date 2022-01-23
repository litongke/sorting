package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.entity.param.UserFjkParam;
import com.chufinfo.sorting.entity.param.UserFjkResultParam;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.FjkMapper;
import com.chufinfo.sorting.model.Fjk;
import com.chufinfo.sorting.model.UserFjk;
import com.chufinfo.sorting.mapper.primary.UserFjkMapper;
import com.chufinfo.sorting.service.IUserFjkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litk
 * @since 2022-01-18
 */
@Service
public class UserFjkServiceImpl extends ServiceImpl<UserFjkMapper, UserFjk> implements IUserFjkService {
    @Autowired
    private FjkMapper fjkMapper;
    @Override
    public UserFjkResultParam jjUserFjk(Long userId) {
        EntityWrapper<Fjk> fjkEntityWrapper=new EntityWrapper<>();
        fjkEntityWrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        List<Fjk> fjkList=fjkMapper.selectList(fjkEntityWrapper);
        EntityWrapper<UserFjk> userFjkEntityWrapper=new EntityWrapper<>();
        userFjkEntityWrapper.eq("user_id",userId);
        userFjkEntityWrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        List<UserFjk> userFjkList=this.baseMapper.selectList(userFjkEntityWrapper);
        List<UserFjkParam> userFjkParamList=new ArrayList<>();
        UserFjkResultParam resultParam=new UserFjkResultParam();
        resultParam.setUserId(userId);
        fjkList.forEach(fjk -> {
            UserFjkParam userFjkParam=new UserFjkParam();
            userFjkParam.setFjkId(fjk.getId());
            userFjkParam.setFjkMc(fjk.getName());
            UserFjk userFjk=userFjkList.stream().filter(item->item.getFjkId().equals(fjk.getId())).findFirst().orElse(null);
            if(userFjk!=null){
                userFjkParam.setSelect(Boolean.TRUE);
                userFjkParam.setId(userFjk.getId());
            }else{
                userFjkParam.setSelect(Boolean.FALSE);
            }
            userFjkParamList.add(userFjkParam);
        });
        resultParam.setFjkParams(userFjkParamList);
        return resultParam;
    }

    @Override
    @Transactional
    public Boolean saveJjUserFjk(UserFjkResultParam userFjkResultParam) {
        Long userId=userFjkResultParam.getUserId();
        EntityWrapper<UserFjk> userFjkEntityWrapper=new EntityWrapper<>();
        userFjkEntityWrapper.eq("user_id",userId);
        this.baseMapper.delete(userFjkEntityWrapper);
        Long[] checkList=userFjkResultParam.getCheckList();
        if(checkList.length>0){
            List<UserFjk> userFjkList=new ArrayList<>();
            for (Long fjkId : checkList) {
                UserFjk userFjk = new UserFjk();
                userFjk.setUserId(userId);
                userFjk.setFjkId(fjkId);
                userFjkList.add(userFjk);
            }
            this.baseMapper.insertBatchUserFjk(userFjkList);
        }
        return Boolean.TRUE;
    }
}
