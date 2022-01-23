package com.chufinfo.sorting.entity.requestParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/18 16:16
 */
@Data
@ApiModel
public class JobCountStatQueryParam {
    /**
     * 工作开始时间
     */
    @ApiModelProperty(value = "工作开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginTime;
    /**
     * 工作结束时间
     */
    @ApiModelProperty(value = "工作结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    /**
     * 操作员用户ID
     */
    @ApiModelProperty(value = "操作员用户ID")
    private Long userId;
}
