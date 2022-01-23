package com.chufinfo.sorting.entity.param;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author litk
 * @since 2022-01-18
 */
@Data
public class UserFjkParam extends Model<UserFjkParam> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("ID")
    private Long id;
    /**
     * 分拣框id
     */
    @ApiModelProperty("分拣框id")
    private Long fjkId;
    /**
     * 分拣框名称
     */
    @ApiModelProperty("分拣框名称")
    private String  fjkMc;
    /**
     * 是否被选择 true-是 false-否
     */
    @ApiModelProperty("是否被选择 true-是 false-否")
    private Boolean select;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
