package com.chufinfo.sorting.service;

import com.baomidou.mybatisplus.service.IService;
import com.chufinfo.sorting.entity.param.FjSgResultParam;
import com.chufinfo.sorting.entity.param.YBXXResultParam;
import com.chufinfo.sorting.model.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litk
 * @since 2022-01-07
 */
public interface IFjPageService extends IService<FjSgResultParam> {
    List<FjSgResultParam> getFjPageFjSgList(User user);
    FjSgResultParam handlerYbxx(String txm, User user);
}
