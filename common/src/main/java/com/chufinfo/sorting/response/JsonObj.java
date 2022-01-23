package com.chufinfo.sorting.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel
public class JsonObj<T> implements Serializable {
    @ApiModelProperty
    private T data;
    private int code;
    private String msg;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public JsonObj(){

    }
    public JsonObj(ErrorCode errorCode){
        this.code=errorCode.getCode();
        this.msg=errorCode.getTip();
    }

    public JsonObj(String errorTip){
        this.code=999999;
        this.msg=errorTip;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
