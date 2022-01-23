package com.chufinfo.sorting.service;

import com.baomidou.mybatisplus.service.IService;
import com.chufinfo.sorting.entity.param.YBXXResultParam;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litk
 * @since 2022-01-07
 */
public interface IYBXXService extends IService<YBXXResultParam> {
    List<YBXXResultParam> getYBXXByTxm(String txm);
}
