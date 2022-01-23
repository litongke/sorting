package com.chufinfo.sorting.mapper.primary;

import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.model.Jymdbm;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@Mapper
public interface JymdbmMapper extends BaseMapper<Jymdbm> {
    List<DictVo> getZyzList();
}
