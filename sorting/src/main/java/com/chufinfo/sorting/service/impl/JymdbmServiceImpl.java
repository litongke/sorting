package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.entity.requestParam.JymdbmFpFjkParam;
import com.chufinfo.sorting.entity.requestParam.JymdbmManageListQueryParam;
import com.chufinfo.sorting.entity.responseParam.JymdbmResultParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.enums.ErrorCode;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.model.Jymdbm;
import com.chufinfo.sorting.mapper.primary.JymdbmMapper;
import com.chufinfo.sorting.service.IFjkService;
import com.chufinfo.sorting.service.IJymdbmService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.utils.SecurityUtils;
import com.chufinfo.sorting.utils.StringUtils;
import com.chufinfo.sorting.utils.sql.PageDataInfo;
import com.chufinfo.sorting.utils.sql.PageHelpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class JymdbmServiceImpl extends ServiceImpl<JymdbmMapper, Jymdbm> implements IJymdbmService {

    @Autowired
    private IFjkService fjkService;

    @Override
    public PageDataInfo getManageList(JymdbmManageListQueryParam jymdbmManageListQueryParam) {
        EntityWrapper<Jymdbm> wrapper=new EntityWrapper<>();
        wrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        if(StringUtils.isNotBlank(jymdbmManageListQueryParam.getMdbm())){
            wrapper.eq("mdbm", jymdbmManageListQueryParam.getMdbm());
        }
        if(jymdbmManageListQueryParam.getFjkId()!=null){
            wrapper.eq("fjk_id", jymdbmManageListQueryParam.getFjkId());
        }
        if(StringUtils.isNotBlank(jymdbmManageListQueryParam.getFjkMc())){
            wrapper.eq("fjk_mc", jymdbmManageListQueryParam.getFjkMc());
        }
        if(StringUtils.isNotBlank(jymdbmManageListQueryParam.getZyzBm())){
            wrapper.eq("zyz_bm", jymdbmManageListQueryParam.getZyzBm());
        }
        wrapper.orderBy("create_time",false);
        PageHelpUtil.startPage();
        List<Jymdbm> jymdbmList=this.baseMapper.selectList(wrapper);
        List<JymdbmResultParam> resultParamList=new ArrayList<>();
        jymdbmList.forEach(t->{
            JymdbmResultParam resultParam=new JymdbmResultParam();
            BeanUtils.copyProperties(t,resultParam);
            resultParamList.add(resultParam);
        });
        return PageHelpUtil.getPage(jymdbmList,resultParamList);
    }

    @Override
    public boolean fpFjk(JymdbmFpFjkParam jymdbmFpFjkParam) {
        Jymdbm jymdbm=this.baseMapper.selectById(jymdbmFpFjkParam.getId());
        if(jymdbm==null){
            throw new RRException("检验目的编码不存在");
        }
        jymdbm.setFjkId(jymdbmFpFjkParam.getFjkId());
        jymdbm.setFjkMc(fjkService.getFjkMcById(jymdbmFpFjkParam.getFjkId()));
        jymdbm.setUserId(SecurityUtils.getUserId());
        if(this.baseMapper.updateById(jymdbm)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<DictVo> getZyzList() {
        List<DictVo> zyzList=this.baseMapper.getZyzList();
        return zyzList;
    }
}
