package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "登录返回参数")
public class LoginResultParam {
    @ApiModelProperty("登录用户")
    private SysUserResultParam sysUser;
    @ApiModelProperty("登录token")
    private String token;
    public LoginResultParam(){

    }
}
