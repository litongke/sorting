package com.chufinfo.sorting.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class JjXjSgjParam {
    /**
     * 交接id=上架id
     */
    private Long jjId;
    /**
     * 交接ids=上架id3
     */
    private List<Long> jjIds;
    /**
     * 用户id
     */
    private Long jjUserId;
}
