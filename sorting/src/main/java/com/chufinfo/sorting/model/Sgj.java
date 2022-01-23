package com.chufinfo.sorting.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
public class Sgj extends Model<Sgj> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 试管架编号
     */
    @TableField("sgj_bh")
    private String sgjBh;

    /**
     * 试管架类型编码
     */
    @TableField("sgj_lx_bm")
    private String sgjLxBm;

    /**
     * 试管架类型
     */
    @TableField("sgj_lx")
    private String sgjLx;

    /**
     * 容量
     */
    @TableField("sgj_rl")
    private Integer sgjRl;

    @TableField("fjk_id")
    private Long fjkId;

    /**
     * 分拣框名称
     */
    @TableField("fjk_mc")
    private String fjkMc;

    /**
     * 删除标记 0、未删除 1、删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
