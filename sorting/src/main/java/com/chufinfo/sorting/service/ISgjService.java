package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.requestParam.FjkAddParam;
import com.chufinfo.sorting.entity.requestParam.SgjAddParam;
import com.chufinfo.sorting.entity.requestParam.SgjManageListQueryParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.model.Sgj;
import com.baomidou.mybatisplus.service.IService;
import com.chufinfo.sorting.utils.sql.PageDataInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
public interface ISgjService extends IService<Sgj> {
    /**
     * 新增/修改 分拣框
     * @param sgjAddParam
     * @return
     */
    boolean saveSgj(SgjAddParam sgjAddParam);

    /**
     * 获取分拣框管理列表
     * @return
     */
    PageDataInfo getManageList(SgjManageListQueryParam sgjManageListQueryParam);

    /**
     * 删除分拣框
     * @param id
     * @return
     */
    boolean delSgj(Long id);

    /**
     * 试管架类型列表
     * @return
     */
    List<DictVo> getLxList();
}
