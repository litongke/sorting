package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.param.UserCdResultParam;
import com.chufinfo.sorting.model.RoleCd;
import com.baomidou.mybatisplus.service.IService;
import com.chufinfo.sorting.model.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litk
 * @since 2022-01-19
 */
public interface IRoleCdService extends IService<RoleCd> {
List<UserCdResultParam> userRoleCdInfo(User user);
}
