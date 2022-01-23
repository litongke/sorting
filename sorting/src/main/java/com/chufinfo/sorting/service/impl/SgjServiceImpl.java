package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.entity.requestParam.FjkAddParam;
import com.chufinfo.sorting.entity.requestParam.SgjAddParam;
import com.chufinfo.sorting.entity.requestParam.SgjManageListQueryParam;
import com.chufinfo.sorting.entity.responseParam.SgjResultParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.enums.DictEnum;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.DictMapper;
import com.chufinfo.sorting.mapper.primary.FjkMapper;
import com.chufinfo.sorting.model.Dict;
import com.chufinfo.sorting.model.Fjk;
import com.chufinfo.sorting.model.Sgj;
import com.chufinfo.sorting.mapper.primary.SgjMapper;
import com.chufinfo.sorting.service.IDictService;
import com.chufinfo.sorting.service.IFjkService;
import com.chufinfo.sorting.service.ISgjService;
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
public class SgjServiceImpl extends ServiceImpl<SgjMapper, Sgj> implements ISgjService {

    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private IDictService dictService;
    @Autowired
    private IFjkService fjkService;

    @Override
    public boolean saveSgj(SgjAddParam sgjAddParam) {
        Sgj sgj=new Sgj();
        BeanUtils.copyProperties(sgjAddParam,sgj);
        sgj.setUserId(SecurityUtils.getUserId());
        sgj.setSgjLx(dictService.getDicValue(sgj.getSgjLxBm(),DictEnum.SGJLX.getCode().toString()));
        sgj.setFjkMc(fjkService.getFjkMcById(sgj.getFjkId()));
        if(sgjAddParam.getId()!=null){
            if(this.baseMapper.updateById(sgj)>0){
                return true;
            }
        }else{
            if(this.baseMapper.insert(sgj)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public PageDataInfo getManageList(SgjManageListQueryParam sgjManageListQueryParam) {
        EntityWrapper<Sgj> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", SystemEnum.delFlag.OK.getCode());
        if (StringUtils.isNotBlank(sgjManageListQueryParam.getSgjBh())) {
            wrapper.eq("sgj_bh", sgjManageListQueryParam.getSgjBh());
        }
        if (StringUtils.isNotBlank(sgjManageListQueryParam.getSgjLxBm())) {
            wrapper.eq("sgj_lx_bm", sgjManageListQueryParam.getSgjLxBm());
        }
        if (sgjManageListQueryParam.getFjkId() != null) {
            wrapper.eq("fjk_id", sgjManageListQueryParam.getFjkId());
        }
        wrapper.orderBy("create_time",false);
        PageHelpUtil.startPage();
        List<Sgj> sgjList = this.baseMapper.selectList(wrapper);
        List<SgjResultParam> resultParamList = new ArrayList<>();
        sgjList.forEach(t -> {
            SgjResultParam resultParam = new SgjResultParam();
            BeanUtils.copyProperties(t, resultParam);
            resultParamList.add(resultParam);
        });
        return PageHelpUtil.getPage(sgjList, resultParamList);
    }

    @Override
    public boolean delSgj(Long id) {
        Sgj sgj=new Sgj();
        sgj.setId(id);
        sgj.setDelFlag(SystemEnum.delFlag.DELETE.getCode());
        sgj.setUserId(SecurityUtils.getUserId());
        if(this.baseMapper.updateById(sgj)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<DictVo> getLxList() {
        List<Dict> dictList=dictMapper.selectList(new EntityWrapper<Dict>().eq("bm_lx", DictEnum.SGJLX.getCode()));
        List<DictVo> dictVoList=new ArrayList<>();
        dictList.forEach(t->{
            DictVo dictVo=new DictVo();
            BeanUtils.copyProperties(t,dictVo);
            dictVoList.add(dictVo);
        });
        return dictVoList;
    }
}
