package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.requestParam.BbmxManageListQueryParam;
import com.chufinfo.sorting.entity.requestParam.JobCountStatQueryParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.model.Bbmx;
import com.baomidou.mybatisplus.service.IService;
import com.chufinfo.sorting.utils.sql.PageDataInfo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
public interface IBbmxService extends IService<Bbmx> {
    /**
     * 获取标本明细管理列表
     * @param bbmxManageListQueryParam
     * @return
     */
    PageDataInfo getManageList(BbmxManageListQueryParam bbmxManageListQueryParam);

    /**
     * 获取分拣框中的试管架列表
     * @param fjkId
     * @return
     */
    List<DictVo> getSgjBhList(Long fjkId);

    /**
     * 工作量统计
     * @param queryParam
     * @return
     */
    PageDataInfo getJobCountStatistics(JobCountStatQueryParam queryParam);
}
