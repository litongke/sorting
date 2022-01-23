package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "开始/结束分拣参数")
public class ModifyStatusQueryParam {
    /**
     * 用户名
     */
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long id;
    /**
     * 工作状态  0、未开始  1、开始
     */
    @NotNull(message = "工作状态不能为空")
    @ApiModelProperty(value = "工作状态  0、未开始  1、开始",required = true)
    private Integer gzStatus;
}

