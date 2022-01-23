package com.chufinfo.sorting.entity.requestParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 14:04
 */
@Data
@ApiModel
public class SgjAddParam {
    private Long Id;

    /**
     * 试管架编号
     */
    @ApiModelProperty(value = "试管架编号")
    @NotNull(message = "试管架编号 不能为空")
    private String sgjBh;

    /**
     * 试管架类型编码
     */
    @ApiModelProperty(value = "试管架类型编码")
    @NotNull(message = "试管架类型编码 不能为空")
    private String sgjLxBm;

    /**
     * 容量
     */
    @ApiModelProperty(value = "容量")
    @NotNull(message = "容量 不能为空")
    private Integer sgjRl;

    /**
     * 分拣框ID
     */
    @ApiModelProperty(value = "分拣框ID")
    @NotNull(message = "分拣框ID 不能为空")
    private Long fjkId;
}
