package com.chufinfo.sorting.mapper.primary;

import com.chufinfo.sorting.entity.requestParam.BbmxManageListQueryParam;
import com.chufinfo.sorting.entity.requestParam.JobCountStatQueryParam;
import com.chufinfo.sorting.entity.responseParam.BbmxResultParam;
import com.chufinfo.sorting.entity.responseParam.JobCountStatResultParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.model.Bbmx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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
public interface BbmxMapper extends BaseMapper<Bbmx> {
    /**
     * 获取标本明细管理列表
     * @param queryParam
     * @return
     */
    List<BbmxResultParam> getManageList(@Param("queryParam")BbmxManageListQueryParam queryParam);

    /**
     * 获取分拣框中的试管架列表
     * @param fjkId
     * @return
     */
    List<DictVo> getSgjBhList(@Param("fjkId")Long fjkId, @Param("userId")Long userId, @Param("createTime")LocalDateTime createTime);

    /**
     * 工作量统计
     * @param queryParam
     * @return
     */
    List<JobCountStatResultParam> getJobCountStatistics(@Param("queryParam")JobCountStatQueryParam queryParam);
}
