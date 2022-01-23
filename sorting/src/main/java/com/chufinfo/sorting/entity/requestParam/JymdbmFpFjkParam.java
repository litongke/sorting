package com.chufinfo.sorting.entity.requestParam;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 16:50
 */
@Data
@ApiModel
public class JymdbmFpFjkParam {

    @NotNull(message = "检验目的编码ID 不能为空")
    private Long id;

    /**
     * 分拣框id
     */
    @ApiModelProperty(value = "分拣框id")
    @NotNull(message = "分拣框id 不能为空")
    private Long fjkId;
}
