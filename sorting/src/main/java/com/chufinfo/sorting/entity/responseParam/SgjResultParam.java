package com.chufinfo.sorting.entity.responseParam;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 17:00
 */
@Data
@ApiModel
public class SgjResultParam {
    private Long id;

    /**
     * 试管架编号
     */
    @ApiModelProperty(value = "试管架编号")
    private String sgjBh;

    /**
     * 试管架类型编码
     */
    @ApiModelProperty(value = "试管架类型编码")
    private String sgjLxBm;

    /**
     * 试管架类型
     */
    @ApiModelProperty(value = "试管架类型")
    private String sgjLx;

    /**
     * 容量
     */
    @ApiModelProperty(value = "容量")
    private Integer sgjRl;

    /**
     * 分拣框ID
     */
    @ApiModelProperty(value = "分拣框ID")
    private Long fjkId;

    /**
     * 分拣框名称
     */
    @ApiModelProperty(value = "分拣框名称")
    private String fjkMc;

}
