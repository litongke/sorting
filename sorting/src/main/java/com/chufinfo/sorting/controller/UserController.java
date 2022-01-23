
package com.chufinfo.sorting.controller;

        import com.chufinfo.sorting.entity.param.LoginQueryParam;
        import com.chufinfo.sorting.entity.param.ModifyStatusQueryParam;
        import com.chufinfo.sorting.entity.param.SysUserResultParam;
        import com.chufinfo.sorting.entity.param.UserFjkQueryParam;
        import com.chufinfo.sorting.response.JsonObj;
        import com.chufinfo.sorting.response.ServerResponseEntity;
        import com.chufinfo.sorting.service.IUserService;
        import com.chufinfo.sorting.utils.SecurityUtils;
        import com.chufinfo.sorting.utils.log.SysLog;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litk
 * @since 2022-01-06
 */
@RestController
@RequestMapping
@Api("登陆/登出")
public class UserController {
    @Autowired
    private IUserService sysUserService;
    /**
     * 登陆
     * @param loginQueryParam
     * @return
     */
    @PostMapping("/auth/login")
    @ApiOperation(value = "登陆",tags = "登陆/登出")
    @SysLog("登陆")
    public JsonObj<LoginQueryParam> login(@RequestBody LoginQueryParam loginQueryParam){
        return ServerResponseEntity.success(sysUserService.login(loginQueryParam));
    }
    /**
     * 登出
     * @return
     */
    @GetMapping("/login/out")
    @ApiOperation(value = "登出",tags = "登陆/登出")
    @SysLog("登出")
    public JsonObj loginOut(){
        sysUserService.loginOut(SecurityUtils.getToken());
        return ServerResponseEntity.success();
    }
    /**
     * 开始/结束分拣
     * @param modifyStatusQueryParam
     * @return
     */
    @PostMapping("/fj/update/gzStatus")
    @ApiOperation(value = "开始/结束分拣",tags = "分拣页面")
    @SysLog("开始/结束分拣")
    public JsonObj<Boolean> fjstatus(@RequestBody ModifyStatusQueryParam modifyStatusQueryParam){
        return ServerResponseEntity.success(sysUserService.modifyStatus(modifyStatusQueryParam));
    }
    /**
     * 工作状态
     * @return
     */
    @GetMapping("/fj/gzStatus")
    @ApiOperation(value = "工作状态",tags = "分拣页面")
    public JsonObj<ModifyStatusQueryParam> fjstatus(){

        return ServerResponseEntity.success(sysUserService.getGzStatus());
    }
    /**
     * 交接用户列表
     * @return
     */
    @GetMapping("/jj/user/fjk/list")
    @ApiOperation(value = "交接用户列表",tags = "用户分拣框")
    public JsonObj<SysUserResultParam> jjUserList(UserFjkQueryParam userFjkQueryParam){

        return ServerResponseEntity.success(sysUserService.jjUserList(userFjkQueryParam));
    }
    /**
     * 操作员下拉选择框
     * @return
     */
    @GetMapping("/stat/user/czry")
    @ApiOperation(value = "操作员下拉选择框",tags = "统计管理")
    public JsonObj<SysUserResultParam> statUserCzry(UserFjkQueryParam userFjkQueryParam){

        return ServerResponseEntity.success(sysUserService.statUserCzry(userFjkQueryParam));
    }
}
