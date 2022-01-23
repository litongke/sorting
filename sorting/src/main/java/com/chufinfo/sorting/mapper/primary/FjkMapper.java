package com.chufinfo.sorting.mapper.primary;

import com.chufinfo.sorting.entity.vo.FjkVo;
import com.chufinfo.sorting.model.Fjk;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface FjkMapper extends BaseMapper<Fjk> {
    List<FjkVo> getQyList();

    List<Fjk> getManageList();
}
