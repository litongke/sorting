package com.chufinfo.sorting.controller;

import com.chufinfo.sorting.entity.requestParam.JobCountStatQueryParam;
import com.chufinfo.sorting.entity.responseParam.JobCountStatResultParam;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IBbmxService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * 统计
 * @author xj
 * @date 2022/1/18 16:11
 */
@RestController
@RequestMapping("/stat")
public class StatisticsController {
    @Autowired
    private IBbmxService bbmxService;

    @GetMapping("jobcount")
    @ApiOperation(value = "工作量统计", tags = "统计管理")
    public JsonObj<JobCountStatResultParam> getJobCountStatistics(JobCountStatQueryParam queryParam){
        return ServerResponseEntity.success(bbmxService.getJobCountStatistics(queryParam));
    }
}
