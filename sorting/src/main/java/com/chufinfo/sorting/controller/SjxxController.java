package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.ISjxxService;
import com.chufinfo.sorting.utils.log.SysLog;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  上下架管理
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/sjxx")
public class SjxxController {
    @Autowired
    private ISjxxService sjxxService;

    @GetMapping("/sj/{sgjBh}")
    @RequiresPermissions("2")
    @ApiOperation(value = "上架试管架", tags = "上下架管理")
    @SysLog("试管架上架")
    public JsonObj putSjxx(@PathVariable String sgjBh){
        return ServerResponseEntity.success(sjxxService.putSjxx(sgjBh));
    }

    @GetMapping("/xj/{sgjBh}")
    @RequiresPermissions("2")
    @ApiOperation(value = "下架试管架", tags = "上下架管理")
    @SysLog("试管架下架")
    public JsonObj offSjxx(@PathVariable String sgjBh){
        return ServerResponseEntity.success(sjxxService.offSjxx(sgjBh));
    }

    @GetMapping("/xj")
    @RequiresPermissions("2")
    @ApiOperation(value = "下架所有试管架", tags = "上下架管理")
    @SysLog("下架所有试管架")
    public JsonObj offAllSjxx(){
        return ServerResponseEntity.success(sjxxService.offAllSjxx());
    }




}
