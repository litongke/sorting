package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.requestParam.FjkAddParam;
import com.chufinfo.sorting.entity.requestParam.FjkManageListQueryParam;
import com.chufinfo.sorting.entity.vo.FjkVo;
import com.chufinfo.sorting.model.Fjk;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IFjkService;
import com.chufinfo.sorting.utils.log.SysLog;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  分拣框管理
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/fjk")
public class FjkController {
    @Autowired
    private IFjkService fjkService;

    @PostMapping("save")
    @RequiresPermissions("1")
    @ApiOperation(value = "分拣框保存", tags = "分拣框管理")
    @SysLog("分拣框保存")
    public JsonObj saveFjk(@RequestBody @Valid FjkAddParam fjkAddParam) {
        return ServerResponseEntity.success(fjkService.saveFjk(fjkAddParam));
    }

    @DeleteMapping("del/{id}")
    @RequiresPermissions("1")
    @ApiOperation(value = "分拣框删除", tags = "分拣框管理")
    @SysLog("分拣框删除")
    public JsonObj delFjl(@PathVariable Long id) {
        return ServerResponseEntity.success(fjkService.delFjk(id));
    }

    @GetMapping("list")
    @RequiresPermissions("1")
    @ApiOperation(value = "获取分拣框列表", tags = "分拣框管理")
    public JsonObj<Fjk> getPage(FjkManageListQueryParam fjkManageListQueryParam) {
        return ServerResponseEntity.success(fjkService.getManageList());
    }

    @GetMapping("color")
    @ApiOperation(value = "获取分拣框颜色列表", tags = "分拣框管理")
    public JsonObj getColorList(){
        return ServerResponseEntity.success(fjkService.getColorList());
    }

    @GetMapping("list/qy")
    @ApiOperation(value = "获取启用的分拣框列表", tags = "分拣框管理")
    public JsonObj<FjkVo> getQyList(){
        return ServerResponseEntity.success(fjkService.getQyList());
    }

}
