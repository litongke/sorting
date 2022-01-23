package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.requestParam.JymdbmFpFjkParam;
import com.chufinfo.sorting.entity.requestParam.JymdbmManageListQueryParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.model.Jymdbm;
import com.baomidou.mybatisplus.service.IService;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.utils.sql.PageDataInfo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
public interface IJymdbmService extends IService<Jymdbm> {
    /**
     * 获取检验目的编码管理列表
     * @param jymdbmManageListQueryParam
     * @return
     */
    PageDataInfo getManageList(JymdbmManageListQueryParam jymdbmManageListQueryParam);

    /**
     * 分配分拣框
     * @param jymdbmFpFjkParam
     * @return
     */
    boolean fpFjk(JymdbmFpFjkParam jymdbmFpFjkParam);

    /**
     * 获取专业组列表
     * @return
     */
    List<DictVo> getZyzList();
}
