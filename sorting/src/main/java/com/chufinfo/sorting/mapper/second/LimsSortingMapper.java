package com.chufinfo.sorting.mapper.second;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chufinfo.sorting.entity.param.YBXXResultParam;
import com.chufinfo.sorting.model.Bbmx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author litk
 * @since 2022-01-07
 */
@Mapper
public interface LimsSortingMapper extends BaseMapper<YBXXResultParam> {
 List<YBXXResultParam> getYBXXByTxm(String txm);
}
