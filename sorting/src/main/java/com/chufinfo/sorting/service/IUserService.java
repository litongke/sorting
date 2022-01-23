package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.param.*;
import com.chufinfo.sorting.model.User;
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
public interface IUserService extends IService<User> {
    LoginResultParam login(LoginQueryParam queryParam);
    boolean loginOut(String token);
    boolean modifyStatus(ModifyStatusQueryParam modifyStatusQueryParam);
    ModifyStatusQueryParam getGzStatus();
    PageDataInfo jjUserList(UserFjkQueryParam userFjkQueryParam);
    List<SysUserResultParam> statUserCzry(UserFjkQueryParam userFjkQueryParam);
}
