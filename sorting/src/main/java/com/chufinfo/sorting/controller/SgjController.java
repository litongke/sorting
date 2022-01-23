package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.requestParam.SgjAddParam;
import com.chufinfo.sorting.entity.requestParam.SgjManageListQueryParam;
import com.chufinfo.sorting.entity.responseParam.SgjResultParam;
import com.chufinfo.sorting.model.Sgj;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.ISgjService;
import com.chufinfo.sorting.utils.log.SysLog;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  试管架管理
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/sgj")
public class SgjController {
    @Autowired
    private ISgjService sgjService;

    @PostMapping("save")
    @RequiresPermissions("1")
    @ApiOperation(value = "试管架保存", tags = "试管架管理")
    @SysLog("试管架保存")
    public JsonObj saveSgj(@RequestBody @Valid SgjAddParam sgjAddParam) {
        return ServerResponseEntity.success(sgjService.saveSgj(sgjAddParam));
    }

    @DeleteMapping("del/{id}")
    @RequiresPermissions("1")
    @ApiOperation(value = "试管架删除", tags = "试管架管理")
    @SysLog("试管架删除")
    public JsonObj delFjl(@PathVariable Long id) {
        return ServerResponseEntity.success(sgjService.delSgj(id));
    }

    @GetMapping("list")
    @RequiresPermissions("1")
    @ApiOperation(value = "获取试管架列表", tags = "试管架管理")
    public JsonObj<SgjResultParam> getPage(SgjManageListQueryParam sgjManageListQueryParam) {
        return ServerResponseEntity.success(sgjService.getManageList(sgjManageListQueryParam));
    }

    @GetMapping("lx")
    @ApiOperation(value = "获取试管架类型列表", tags = "试管架管理")
    public JsonObj getLxList(){
        return ServerResponseEntity.success(sgjService.getLxList());
    }



}
