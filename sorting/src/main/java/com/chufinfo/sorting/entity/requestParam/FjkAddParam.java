package com.chufinfo.sorting.entity.requestParam;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/7 11:04
 */
@Data
@ApiModel(value = "分拣框新增修改",description = "分拣框管理")
public class FjkAddParam {

    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称 不能为空")
    private String name;

    /**
     * 颜色编码
     */
    @ApiModelProperty(value = "颜色编码")
    @NotBlank(message = "颜色编码 不能为空")
    private String colorBm;

    /**
     * 是否使用试管架：0、是 1、否
     */
    @ApiModelProperty(value = "是否使用试管架：0、是 1、否")
    @NotNull(message = "是否使用试管架 不能为空")
    private Integer status;
}
