package com.chufinfo.sorting.entity.requestParam;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/7 11:25
 */
@Data
@ApiModel(value = "分拣框管理列表查询参数")
public class FjkManageListQueryParam {
    private Integer pageSize;
    private Integer pageNum;
}
