package com.chufinfo.sorting.entity.requestParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 15:07
 */
@Data
@ApiModel
public class SgjManageListQueryParam {
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
     * 分拣筐ID
     */
    @ApiModelProperty(value = "分拣筐ID")
    private Long fjkId;

    private Integer pageSize;
    private Integer pageNum;
}
