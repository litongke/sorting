package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "用户登录参数")
public class LoginQueryParam {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;



}
