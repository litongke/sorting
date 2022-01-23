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
public class Sjxx extends Model<Sjxx> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 试管架ID
     */
    @TableField("sgj_id")
    private Long sgjId;

    /**
     * 分拣框ID
     */
    @TableField("fjk_id")
    private Long fjkId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 交接用户
     */
    @TableField("jj_user_id")
    private Long jjUserId;
    /**
     * 加入试管架样本数量
     */
    @TableField("ybsl")
    private Integer ybsl;
    /**
     * 0、上架 1、下架  2、已交接
     */
    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
