package com.chufinfo.sorting.entity.param;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
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
@ApiModel("用户分拣框")
public class UserFjkResultParam extends Model<UserFjkResultParam> {

    private static final long serialVersionUID = 1L;

    /**
     * 选中用户id
     */
    @ApiModelProperty("选中用户id")
    private Long userId;
    /**
     * 选中复选框ids
     */
    @ApiModelProperty("选中复选框ids")
    private Long[] checkList;
    /**
     * 用户复选框列表
     */
    @ApiModelProperty("用户复选框列表")
    private List<UserFjkParam> fjkParams;
    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
