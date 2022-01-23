package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.param.UserCdResultParam;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IRoleCdService;
import com.chufinfo.sorting.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litk
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/user/cd")
public class RoleCdController {
    @Autowired
    private IRoleCdService iUserRoleCdService;
    @GetMapping("/info")
    @ApiOperation(value = "用户菜单",tags = "菜单/角色/用户")
    public JsonObj<UserCdResultParam> userRoleCdInfo(){
        User user= SecurityUtils.getUser();
        return ServerResponseEntity.success(iUserRoleCdService.userRoleCdInfo(user));
    }
}
