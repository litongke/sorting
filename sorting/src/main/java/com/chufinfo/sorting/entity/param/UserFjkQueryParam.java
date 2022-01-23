package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author litk
 * @date 2022/1/11 15:07
 */
@Data
@ApiModel
public class UserFjkQueryParam {

    private Integer pageSize;
    private Integer pageNum;
}
