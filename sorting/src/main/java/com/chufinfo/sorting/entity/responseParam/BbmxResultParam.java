package com.chufinfo.sorting.entity.responseParam;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/12 11:47
 */
@Data
@ApiModel
public class BbmxResultParam {

    private Long id;
    /**
     * 标本号(条形码)
     */
    @ApiModelProperty(value = "标本号(条形码)")
    private String txm;
    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String hzxm;

    /**
     * 检验目的名称
     */
    @ApiModelProperty(value = "检验目的名称")
    private String jymdmc;
    /**
     * 分拣用户
     */
    @ApiModelProperty(value = "分拣用户")
    private Long smUserId;
    /**
     * 分拣用户姓名
     */
    @ApiModelProperty(value = "分拣用户姓名")
    private String smUserName;

    /**
     * 扫码时间
     */
    @ApiModelProperty(value = "扫码时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date smTime;

    /**
     * 分拣框编号
     */
    @ApiModelProperty(value = "分拣框编号")
    private Long fjkId;

    /**
     * 分拣框名称
     */
    @ApiModelProperty(value = "分拣框名称")
    private String fjkMc;

    /**
     * 试管架编号
     */
    @ApiModelProperty(value = "试管架编号")
    private String sgjBh;

    /**
     * 试管架流水号
     */
    @ApiModelProperty(value = "试管架流水号")
    private Integer sgLsh;

    /**
     * 状态 0、未交接  1、已交接
     */
    @ApiModelProperty(value = "标本状态 0、未交接  1、已交接")
    private Integer status;

    @ApiModelProperty(value = "标本状态名称")
    private String statusName;

    /**
     * 交接用户
     */
    @ApiModelProperty(value = "交接用户")
    private Long jjUserId;

    /**
     * 交接用户姓名
     */
    @ApiModelProperty(value = "交接用户姓名")
    private String jjUserName;

    /**
     * 交接时间
     */
    @ApiModelProperty(value = "交接时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jjTime;
}
