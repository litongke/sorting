package com.chufinfo.sorting.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2022-01-11
 */
@Data
@Accessors(chain = true)
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String bm;

    /**
     * 值
     */
    @TableField("bm_value")
    private String bmValue;

    /**
     * 字典项类型
     */
    @TableField("bm_lx")
    private String bmLx;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
