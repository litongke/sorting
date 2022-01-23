package com.chufinfo.sorting.entity.responseParam;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 16:23
 */
@Data
@ApiModel
public class JymdbmResultParam {
    private Long id;

    /**
     * 目的编码
     */
    @ApiModelProperty(value = "检验目的编码")
    private String mdbm;

    /**
     * 目的名称
     */
    @ApiModelProperty(value = "检验目的名称")
    private String mdmc;

    /**
     * 专业组
     */
    @ApiModelProperty(value = "专业组")
    private String zyz;

    /**
     * 分拣框id
     */
    @ApiModelProperty(value = "分拣框id")
    private Long fjkId;

    /**
     * 分拣框名称
     */
    @ApiModelProperty(value = "分拣框名称")
    private String fjkMc;
}
