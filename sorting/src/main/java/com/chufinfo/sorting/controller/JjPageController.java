package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.param.JjSgjResultParam;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.model.*;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IJjPageService;
import com.chufinfo.sorting.utils.SecurityUtils;
import com.chufinfo.sorting.utils.log.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
@RequestMapping("/jj/page")
public class JjPageController {
    @Autowired
    private IJjPageService iJjPageService;
    @GetMapping("list")
    @ApiOperation(value = "交接试管列表", tags = "交接页面")
    public JsonObj<JjSgjResultParam> getJjPageList() {
        User user= SecurityUtils.getUser();
        return ServerResponseEntity.success(iJjPageService.getJjPageSgjList(user));
    }
    @PostMapping("confirm")
    @ApiOperation(value = "确认交接 ", tags = "交接页面")
    @SysLog("确认交接")
    public JsonObj confirmJJ(@RequestBody List<JjSgjResultParam> jjSgjResultParamList){
        User user= SecurityUtils.getUser();
        if(user==null||!Objects.equals(SystemEnum.userLevel.JJRY.getCode(), user.getLevel())){
            return ServerResponseEntity.fail("用户没有交接权限");
        }
        iJjPageService.confirmJJSGJ(user,jjSgjResultParamList);
        return ServerResponseEntity.success();
    }

}
