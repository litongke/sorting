package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.param.FjSgResultParam;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.model.*;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IFjPageService;
import com.chufinfo.sorting.utils.SecurityUtils;
import com.chufinfo.sorting.utils.log.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litk
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/fj/page")
public class FjPageController {
    @Autowired
    private IFjPageService iFjPageService;
    @GetMapping("list")
    @ApiOperation(value = "分拣试管列表", tags = "分拣页面")
    public JsonObj<FjSgResultParam> getFjPageList() {
        User user= SecurityUtils.getUser();
        return ServerResponseEntity.success(iFjPageService.getFjPageFjSgList(user));
    }
    @GetMapping("{txm}")
    @ApiOperation(value = "扫条形码 ", tags = "分拣页面")
    @SysLog("扫条形码")
    public JsonObj<FjSgResultParam> userSaoTxm(@PathVariable String txm){
        User user= SecurityUtils.getUser();
        if(user==null||!Objects.equals(SystemEnum.userLevel.FJRY.getCode(), user.getLevel())){
            return ServerResponseEntity.fail("用户没有分拣权限");
        }
        if(user.getKsTime()==null|| Objects.equals(SystemEnum.gzStatus.END.getCode(), user.getGzStatus())){
            return ServerResponseEntity.fail("用户还没有开始分拣");
        }
        return ServerResponseEntity.success(iFjPageService.handlerYbxx(txm,user));
    }

}
