package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.param.SysUserResultParam;
import com.chufinfo.sorting.entity.param.UserFjkResultParam;
import com.chufinfo.sorting.model.UserFjk;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litk
 * @since 2022-01-18
 */
public interface IUserFjkService extends IService<UserFjk> {
    UserFjkResultParam jjUserFjk(Long userId);
    Boolean saveJjUserFjk(UserFjkResultParam userFjkResultParam);
}
