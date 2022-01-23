package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 交接页面对象
 * 试管架 样本
 *
 * @author litk
 * @date 2022/1/11
 */
@Data
@ApiModel
public class JjSgjResultParam {

    /**
     * 交接id
     */
    @ApiModelProperty(value = "交接id")
    private Long jjId;
    /**
     * 分拣框id
     */
    @ApiModelProperty(value = "分拣框id")
    private Long fjkId;
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
}
