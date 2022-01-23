package com.chufinfo.sorting.controller;


import com.chufinfo.sorting.entity.param.UserFjkResultParam;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.response.JsonObj;
import com.chufinfo.sorting.response.ServerResponseEntity;
import com.chufinfo.sorting.service.IUserFjkService;
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
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/jj/user/fjk")
public class UserFjkController {
    @Autowired
    private IUserFjkService iUserFjkService;
    /**
     * 交接用户分拣框列表
     * @return
     */
    @GetMapping("/{userId}")
    @ApiOperation(value = "交接用户分拣框列表",tags = "用户分拣框")
    public JsonObj<UserFjkResultParam> jjUserFjk(@PathVariable Long userId){

        return ServerResponseEntity.success(iUserFjkService.jjUserFjk(userId));
    }
    /**
     * 保存交接用户分拣框
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存交接用户分拣框",tags = "用户分拣框")
    @SysLog("保存交接用户分拣框")
    public JsonObj saveJjUserFjk(@RequestBody  UserFjkResultParam userFjkResultParam){
        User user= SecurityUtils.getUser();
        if(user==null||!Objects.equals(SystemEnum.userLevel.ADMIN.getCode(), user.getLevel())){
            return ServerResponseEntity.fail("只有管理员才能够操作");
        }
        return ServerResponseEntity.success(iUserFjkService.saveJjUserFjk(userFjkResultParam));
    }
}
