package com.chufinfo.sorting.entity.responseParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/18 16:19
 */
@Data
@ApiModel
public class JobCountStatResultParam {
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 分拣数量
     */
    @ApiModelProperty(value = "分拣数量")
    private Integer jobCount;
}
