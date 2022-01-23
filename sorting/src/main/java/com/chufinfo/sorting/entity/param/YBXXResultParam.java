package com.chufinfo.sorting.entity.param;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class YBXXResultParam extends Model<YBXXResultParam> {
    /**
     * 流水号
     */
    @TableField("keyid")
    private String keyid;
    /**
     * 标本号(条形码)
     */
    @TableField("txm")
    private String txm;
    /**
     * 检验目的
     */
    @TableField("jymdbm")
    private String jymdbm;
    /**
     * 检验目的名称
     */
    @TableField("jymdmc")
    private String jymdmc;
    /**
     * 患者名称
     */
    @TableField("hzxm")
    private String hzxm;
    /**
     * 专业组
     */
    @TableField("zyz")
    private String zyz;
    /**
     * 专业组编码
     */
    @TableField("zyz_bm")
    private String zyzbm;
    /**
     * 送检机构编码
     */
    @TableField("jgdm")
    private String jgdm;
    /**
     * 送检机构名称
     */
    @TableField("jigmc")
    private String jigmc;
    /**
     * 创建时间
     */
    @TableField("createtime")
    private String createtime;

    @Override
    protected Serializable pkVal() {
        return this.keyid;
    }
}
