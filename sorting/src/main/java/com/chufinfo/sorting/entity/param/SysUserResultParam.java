package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 */
@Data
@ApiModel("登录用户")
public class SysUserResultParam implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;


    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 1、管理员  2、分拣员 3、交接员
     */
    @ApiModelProperty("1、管理员  2、分拣员 3、交接员")
    private Integer level;

    /**
     * 工作状态  0、未开始  1、开始
     */
    @ApiModelProperty("工作状态  0、未开始  1、开始")
    private Integer gzStatus;

    /**
     * 工作开始时间
     */
    @ApiModelProperty("工作开始时间")
    private Date ksTime;

}
