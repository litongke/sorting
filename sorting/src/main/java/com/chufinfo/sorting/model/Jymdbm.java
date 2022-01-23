package com.chufinfo.sorting.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
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
 * @author xiejun
 * @since 2022-01-07
 */
@Data
@Accessors(chain = true)
public class Jymdbm extends Model<Jymdbm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 目的编码
     */
    private String mdbm;

    /**
     * 目的名称
     */
    private String mdmc;

    /**
     * 分拣框id
     */
    @TableField("fjk_id")
    private Long fjkId;

    /**
     * 分拣框名称
     */
    @TableField("fjk_mc")
    private String fjkMc;

    /**
     * 专业组编码
     */
    @TableField("zyz_bm")
    private String zyzBm;

    /**
     * 专业组
     */
    private String zyz;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 删除标志  0、未删除 1、删除
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
