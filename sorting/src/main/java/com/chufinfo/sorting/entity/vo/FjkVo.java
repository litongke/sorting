package com.chufinfo.sorting.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 14:28
 */
@Data
@ApiModel
public class FjkVo {
    @ApiModelProperty(value = "分拣框ID")
    private Long fjkId;
    @ApiModelProperty(value = "分拣框名称")
    private String fjkMc;
    @ApiModelProperty(value = "分拣框颜色")
    private String color;
}
