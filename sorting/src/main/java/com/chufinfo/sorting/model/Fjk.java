package com.chufinfo.sorting.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@Data
@Accessors(chain = true)
@ApiModel
public class Fjk extends Model<Fjk> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    @ApiModelProperty(value = "创建用户ID")
    private Long userId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    private String color;

    /**
     * 颜色编码
     */
    @TableField("color_bm")
    @ApiModelProperty(value = "颜色编码")
    private String colorBm;

    /**
     * 是否使用试管架：0、是 1、否
     */
    @ApiModelProperty(value = "是否使用试管架：0、是 1、否")
    private Integer status;

    /**
     * 0、未删除  1、已删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 专业组
     */
    @TableField(exist = false)
    private String zyz;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
