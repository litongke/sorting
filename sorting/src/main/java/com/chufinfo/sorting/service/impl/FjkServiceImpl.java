package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.entity.requestParam.FjkAddParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.entity.vo.FjkVo;
import com.chufinfo.sorting.enums.DictEnum;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.DictMapper;
import com.chufinfo.sorting.model.Dict;
import com.chufinfo.sorting.model.Fjk;
import com.chufinfo.sorting.mapper.primary.FjkMapper;
import com.chufinfo.sorting.service.IFjkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.utils.SecurityUtils;
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
public class FjkServiceImpl extends ServiceImpl<FjkMapper, Fjk> implements IFjkService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public boolean saveFjk(FjkAddParam fjkAddParam) {
        Fjk fjk=new Fjk();
        BeanUtils.copyProperties(fjkAddParam,fjk);
        fjk.setUserId(SecurityUtils.getUserId());
        fjk.setColor(this.getColorByBm(fjk.getColorBm()));
        if(fjkAddParam.getId()!=null){
            if(this.baseMapper.updateById(fjk)>0){
                return true;
            }
        }else{
            if(this.baseMapper.insert(fjk)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public PageDataInfo getManageList() {
        PageHelpUtil.startPage();
//        List<Fjk> fjkList=this.baseMapper.selectList(new EntityWrapper<Fjk>()
//                .eq("del_flag", SystemEnum.delFlag.OK.getCode())
//                .orderBy("create_time",false)
//        );
        List<Fjk> fjkList=this.baseMapper.getManageList();
        return PageHelpUtil.getPage(fjkList);
    }

    @Override
    public boolean delFjk(Long id) {
        Fjk fjk=new Fjk();
        fjk.setId(id);
        fjk.setDelFlag(SystemEnum.delFlag.DELETE.getCode());
        fjk.setUserId(SecurityUtils.getUserId());
        if(this.baseMapper.updateById(fjk)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<DictVo> getColorList() {
        List<Dict> dictList=dictMapper.selectList(new EntityWrapper<Dict>().eq("bm_lx", DictEnum.color.getCode()));
        List<DictVo> dictVoList=new ArrayList<>();
        dictList.forEach(t->{
            DictVo dictVo=new DictVo();
            BeanUtils.copyProperties(t,dictVo);
            dictVoList.add(dictVo);
        });
        return dictVoList;
    }

    @Override
    public List<FjkVo> getQyList() {
        List<FjkVo> fjkVoList=this.baseMapper.getQyList();
        return fjkVoList;
    }

    @Override
    public String getFjkMcById(Long fjkId) {
        Fjk fjk=this.baseMapper.selectById(fjkId);
        if(fjk==null){
            throw new RRException("分拣框不存在");
        }
        return fjk.getName();
    }

    /**
     * 获取颜色
     * @param bm
     * @return
     */
    private String getColorByBm(String bm){
        Dict query=new Dict();
        query.setBm(bm);
        query.setBmLx(DictEnum.color.getCode().toString());
        Dict color=dictMapper.selectOne(query);
        if(color==null){
            throw new RRException("颜色不存在");
        }
        return color.getBmValue();
    }
}
