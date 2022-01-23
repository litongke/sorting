package com.chufinfo.sorting.entity.param;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author litk
 * @since 2022-01-19
 */
@Data
@ApiModel("用户角色菜单")
public class UserCdResultParam extends Model<UserCdResultParam> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    private Long id;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String cdMc;

    /**
     * 菜单编号
     */
    @ApiModelProperty("菜单编号")
    private Integer cdBh;

    /**
     * 菜单地址
     */
    @ApiModelProperty("菜单地址")
    private String cdUrl;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
