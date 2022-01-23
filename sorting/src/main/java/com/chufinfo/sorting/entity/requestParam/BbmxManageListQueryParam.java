package com.chufinfo.sorting.entity.requestParam;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/12 11:43
 */
@Data
@ApiModel
public class BbmxManageListQueryParam {
    /**
     * 扫码开始时间
     */
    @ApiModelProperty(value = "扫码开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginTime;
    /**
     * 扫码结束时间
     */
    @ApiModelProperty(value = "扫码结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    /**
     * 标本号
     */
    @ApiModelProperty(value = "标本号")
    private String txm;
    /**
     * 分拣框编号
     */
    @ApiModelProperty(value = "分拣框编号")
    private Long fjkId;
    /**
     * 试管架编号
     */
    @ApiModelProperty(value = "试管架编号")
    private String sgjBh;
    /**
     * 标本状态
     */
    @ApiModelProperty(value = "标本状态")
    private Integer status;

    /**
     * 0、标本明细页面 1、分拣页面
     */
    @ApiModelProperty(value = "0、标本明细页面 1、分拣页面")
    private Integer page;

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String hzxm;
}
