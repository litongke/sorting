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
public class Bbmx extends Model<Bbmx> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分拣用户
     */
    @TableField("sm_user_id")
    private Long smUserId;

    /**
     * 扫码时间
     */
    @TableField("sm_time")
    private Date smTime;
    /**
     * 上架ID
     */
    @TableField("sj_id")
    private Long sjId;

    @TableField("fjk_id")
    private Long fjkId;

    /**
     * 分拣框名称
     */
    @TableField("fjk_mc")
    private String fjkMc;

    @TableField("sgj_id")
    private Long sgjId;

    /**
     * 试管架编号
     */
    @TableField("sgj_bh")
    private String sgjBh;

    /**
     * 试管架流水号
     */
    @TableField("sg_lsh")
    private Integer sgLsh;

    /**
     * 标本号(条形码)
     */
    private String txm;

    /**
     * 患者姓名
     */
    private String hzxm;

    /**
     * 检验目的
     */
    private String jymdbm;

    /**
     * 检验目的名称
     */
    private String jymdmc;
    /**
     * 送检机构
     */
    private String jgdm;

    /**
     * 送检机构名称
     */
    private String jigmc;

    /**
     * 状态 0、未交接  1、已交接
     */
    private Integer status;

    /**
     * 交接用户
     */
    @TableField("jj_user_id")
    private Long jjUserId;

    /**
     * 交接时间
     */
    @TableField("jj_time")
    private Date jjTime;

    /**
     * 删除标志 0、未删除  1、删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
