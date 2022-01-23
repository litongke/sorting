package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.requestParam.FjkAddParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.entity.vo.FjkVo;
import com.chufinfo.sorting.model.Fjk;
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
public interface IFjkService extends IService<Fjk> {
    /**
     * 新增/修改 分拣框
     * @param fjkAddParam
     * @return
     */
    boolean saveFjk(FjkAddParam fjkAddParam);

    /**
     * 获取分拣框管理列表
     * @return
     */
    PageDataInfo getManageList();

    /**
     * 删除分拣框
     * @param id
     * @return
     */
    boolean delFjk(Long id);

    /**
     * 分拣框颜色列表
     * @return
     */
    List<DictVo> getColorList();

    /**
     * 获取启用的分拣框
     * @return
     */
    List<FjkVo> getQyList();

    /**
     * 获取分拣框名称
     * @param fjkId
     * @return
     */
    String getFjkMcById(Long fjkId);
}
