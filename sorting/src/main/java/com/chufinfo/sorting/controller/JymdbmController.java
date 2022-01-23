package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.requestParam.JymdbmFpFjkParam;
import com.chufinfo.sorting.entity.requestParam.JymdbmManageListQueryParam;
import com.chufinfo.sorting.entity.responseParam.JymdbmResultParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IJymdbmService;
import com.chufinfo.sorting.utils.log.SysLog;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  检验目的编码管理
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/jymdbm")
public class JymdbmController {
    @Autowired
    private IJymdbmService jymdbmService;

    @GetMapping("list")
    @RequiresPermissions("1")
    @ApiOperation(value = "检验目的编码列表", tags = "检验目的编码管理")
    public JsonObj<JymdbmResultParam> getManageList(JymdbmManageListQueryParam jymdbmManageListQueryParam){
        return ServerResponseEntity.success(jymdbmService.getManageList(jymdbmManageListQueryParam));
    }

    @PutMapping("fpfjk")
    @RequiresPermissions("1")
    @ApiOperation(value = "分配分拣框", tags = "检验目的编码管理")
    @SysLog("分配分拣框")
    public JsonObj fpFjk(@RequestBody @Valid JymdbmFpFjkParam jymdbmFpFjkParam){
        return ServerResponseEntity.success(jymdbmService.fpFjk(jymdbmFpFjkParam));
    }

    @GetMapping("zyz")
    @ApiOperation(value = "获取专业组列表", tags = "检验目的编码管理")
    public JsonObj<DictVo> getZyzList(){
        return ServerResponseEntity.success(jymdbmService.getZyzList());
    }

}
