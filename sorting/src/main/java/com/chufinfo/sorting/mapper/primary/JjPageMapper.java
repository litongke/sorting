package com.chufinfo.sorting.mapper.primary;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chufinfo.sorting.entity.param.FjSgResultParam;
import com.chufinfo.sorting.entity.param.JjSgjResultParam;
import com.chufinfo.sorting.entity.param.JjXjSgjParam;
import com.chufinfo.sorting.model.Sjxx;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

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
public interface JjPageMapper extends BaseMapper<JjSgjResultParam> {
    void updateBatchSjxx(JjXjSgjParam param);
    void updateBatchBbmx(JjXjSgjParam param);
}
