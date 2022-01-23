package com.chufinfo.sorting.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.BbmxMapper;
import com.chufinfo.sorting.mapper.primary.SgjMapper;
import com.chufinfo.sorting.mapper.primary.UserMapper;
import com.chufinfo.sorting.model.Bbmx;
import com.chufinfo.sorting.model.Sgj;
import com.chufinfo.sorting.model.Sjxx;
import com.chufinfo.sorting.mapper.primary.SjxxMapper;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.service.ISjxxService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@Service
public class SjxxServiceImpl extends ServiceImpl<SjxxMapper, Sjxx> implements ISjxxService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SgjMapper sgjMapper;
    @Autowired
    private BbmxMapper bbmxMapper;

    @Override
    public boolean putSjxx(String sgjBh) {
        Long userId=SecurityUtils.getUserId();
        User user=userMapper.selectById(userId);
        if(user.getGzStatus().equals(SystemEnum.gzStatus.END.getCode())){
            throw new RRException("请先点击开始分拣按钮");
        }
        Sgj sgjQuery=new Sgj();
        sgjQuery.setSgjBh(sgjBh);
        sgjQuery.setDelFlag(SystemEnum.delFlag.OK.getCode());
        Sgj sgj=sgjMapper.selectOne(sgjQuery);
        if(sgj==null){
            throw new RRException("请先新增试管架");
        }
        List<Sjxx> sjxxList=this.baseMapper.selectList(new EntityWrapper<Sjxx>()
//                .eq("sgj_id",sgj.getId())
                .eq("fjk_id",sgj.getFjkId())
                .eq("user_id",userId)
                .orderBy("update_time",false)
                .last("limit 1")
        );
        Sjxx lastSjxx=null;
        if(CollectionUtil.isNotEmpty(sjxxList)){
            lastSjxx=sjxxList.get(0);
        }
        Sjxx sjxx=new Sjxx();
        sjxx.setFjkId(sgj.getFjkId());
        sjxx.setStatus(SystemEnum.sgjStatus.SJSGJ.getCode());
        sjxx.setSgjId(sgj.getId());
        sjxx.setUserId(userId);
        if(lastSjxx==null || lastSjxx.getStatus().equals(SystemEnum.sgjStatus.JJSGJ.getCode())){
            if(this.baseMapper.insert(sjxx)>0){
                return true;
            }
        }else if(lastSjxx.getSgjId().equals(sgj.getId())){
            if(lastSjxx.getStatus().equals(SystemEnum.sgjStatus.SJSGJ.getCode())) {
                throw new RRException("不可重复上架试管架");
            }else if(lastSjxx.getStatus().equals(SystemEnum.sgjStatus.XJSGJ.getCode())){
                sjxx.setId(lastSjxx.getId());
                if(this.baseMapper.updateById(sjxx)>0){
                    return true;
                }
            }
        }else{
            if(lastSjxx.getStatus().equals(SystemEnum.sgjStatus.SJSGJ.getCode())) {
                //先下架
                Sjxx updateSjxx=new Sjxx();
                updateSjxx.setId(lastSjxx.getId());
                updateSjxx.setStatus(SystemEnum.sgjStatus.XJSGJ.getCode());
                if(this.baseMapper.updateById(updateSjxx)<=0){
                    return false;
                }
            }
            if(this.baseMapper.insert(sjxx)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offSjxx(String sgjBh) {
        Long userId=SecurityUtils.getUserId();
        Sgj sgjQuery=new Sgj();
        sgjQuery.setSgjBh(sgjBh);
        sgjQuery.setDelFlag(SystemEnum.delFlag.OK.getCode());
        Sgj sgj=sgjMapper.selectOne(sgjQuery);
        if(sgj==null){
            throw new RRException("试管架不存在");
        }
        List<Sjxx> sjxxList=this.baseMapper.selectList(new EntityWrapper<Sjxx>()
                .eq("sgj_id",sgj.getId())
                .eq("user_id",userId)
                .orderBy("create_time",false)
        );
        Sjxx lastSjxx=null;
        if(CollectionUtil.isNotEmpty(sjxxList)){
            lastSjxx=sjxxList.get(0);
        }
        if(lastSjxx==null|| lastSjxx.getStatus().equals(SystemEnum.sgjStatus.XJSGJ.getCode()) || lastSjxx.getStatus().equals(SystemEnum.sgjStatus.JJSGJ.getCode())){
            throw new RRException("试管架下架失败");
        }
        Sjxx sjxx=new Sjxx();
        sjxx.setStatus(SystemEnum.sgjStatus.XJSGJ.getCode());
        sjxx.setId(lastSjxx.getId());
        if(this.baseMapper.updateById(sjxx)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean offAllSjxx() {
        Long userId=SecurityUtils.getUserId();
        Sjxx sjxx=new Sjxx();
        sjxx.setStatus(SystemEnum.sgjStatus.XJSGJ.getCode());
        if(this.baseMapper.update(sjxx,new EntityWrapper<Sjxx>()
                .eq("user_id",userId)
                .eq("status",SystemEnum.sgjStatus.SJSGJ.getCode())
        )>0){
            return true;
        }
        return false;
    }
}
