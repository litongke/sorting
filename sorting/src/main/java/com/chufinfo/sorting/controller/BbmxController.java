package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.requestParam.BbmxManageListQueryParam;
import com.chufinfo.sorting.entity.responseParam.BbmxResultParam;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IBbmxService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  标本明细管理
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/bbmx")
public class BbmxController {
    @Autowired
    private IBbmxService bbmxService;

    @GetMapping("list")
    @ApiOperation(value = "获取标本明细管理列表", tags = "标本明细管理")
    public JsonObj<BbmxResultParam> getManageList(BbmxManageListQueryParam bbmxManageListQueryParam) {
        return ServerResponseEntity.success(bbmxService.getManageList(bbmxManageListQueryParam));
    }

    @GetMapping("sgj/{fjkId}")
    @ApiOperation(value = "获取分拣框中的试管架列表", tags = "标本明细管理")
    public JsonObj getSgjBhList(@PathVariable Long fjkId) {
        return ServerResponseEntity.success(bbmxService.getSgjBhList(fjkId));
    }
}
