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
@TableName("role_cd")
public class RoleCd extends Model<RoleCd> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色 1-管理员 2-分拣人员 3-交接人员
     */
    private Integer level;

    /**
     * 菜单编号
     */
    @TableField("cd_bh")
    private Integer cdBh;

    /**
     * 删除标识 0-未删除 1-已删除
     */
    @TableField("del_flag")
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
