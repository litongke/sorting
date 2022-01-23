package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分拣页面对象
 * 分拣框 试管架 样本
 *
 * @author litk
 * @date 2022/1/11
 */
@Data
@ApiModel
public class FjSgResultParam {

    /**
     * 分拣框ID
     */
    @ApiModelProperty(value = "分拣框id")
    private Long fjkId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "分拣框名称")
    private String fjkMc;


    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    private String color;
    /**
     * 上架中试管架id
     */
    @ApiModelProperty(value = "试管架id")
    private Long sgjId;

    /**
     * 试管架编号
     */
    @ApiModelProperty(value = "试管架编号")
    private String sgjBh;
    /**
     * 容量
     */
    @ApiModelProperty(value = "试管架容量")
    private Integer sgjRl;
    /**
     * 试管架中样本数量
     */
    @ApiModelProperty(value = "试管架中样本数量")
    private Integer ybSL;
    /**
     * 分拣框中试管架数量
     */
    @ApiModelProperty(value = "分拣框中试管架数量")
    private Integer sgjSL;
}
