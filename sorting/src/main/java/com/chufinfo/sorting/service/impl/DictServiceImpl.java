package com.chufinfo.sorting.service.impl;

import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.enums.DictEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.model.Dict;
import com.chufinfo.sorting.mapper.primary.DictMapper;
import com.chufinfo.sorting.service.IDictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-11
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public String getDicValue(String bm, String lx) {
        Dict query=new Dict();
        query.setBm(bm);
        query.setBmLx(lx);
        Dict dic=this.baseMapper.selectOne(query);
        if(dic==null){
            throw new RRException("数据字段不存在");
        }
        return dic.getBmValue();
    }
}
