package com.chufinfo.sorting.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author litk
 * @since 2022-01-19
 */
@Data
@Accessors(chain = true)
@TableName("cd_info")
public class CdInfo extends Model<CdInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("cd_mc")
    private String cdMc;

    /**
     * 菜单编号
     */
    @TableField("cd_bh")
    private Integer cdBh;

    /**
     * 菜单地址
     */
    @TableField("cd_url")
    private String cdUrl;

    /**
     * 删除标识 0-未删除 1-删除
     */
    @TableField("del_flag")
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
