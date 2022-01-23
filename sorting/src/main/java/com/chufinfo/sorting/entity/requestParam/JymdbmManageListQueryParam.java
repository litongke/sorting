package com.chufinfo.sorting.entity.requestParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 16:15
 */
@Data
@ApiModel
public class JymdbmManageListQueryParam {
    /**
     * 检验目的编码
     */
    @ApiModelProperty(value = "检验目的编码")
    private String mdbm;
    /**
     * 分检筐ID
     */
    @ApiModelProperty(value = "分检筐ID")
    private Long fjkId;
    /**
     * 分检筐名称
     */
    @ApiModelProperty(value = "分检筐名称")
    private String fjkMc;
    /**
     * 专业组编码
     */
    @ApiModelProperty(value = "专业组编码")
    private String zyzBm;

    private Integer pageSize;
    private Integer pageNum;
}
