package com.chufinfo.sorting.enums;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/11 11:16
 */
public enum DictEnum {
    color(10, "颜色"),
    SGJLX(12, "试管架类型"),
    ;
    private  Integer code;
    private  String message;

    DictEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return message;
    }
}
