package com.chufinfo.sorting.response;


/**
 * @author kenyon
 * @date 2021-02-25 11:04
 * @description 统一响应结果实体
 */
public class ResponseResult<T> {
    private Integer code = 200;
    private String msg = "success";
    private T data;
    /*是否成功 1：成功 -1：失败*/
    private Integer success = 1;

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult(String msg) {
        this.success = -1;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg) {
        this.data = null;
        this.success = -1;
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(data);
    }

    /**
     * 失败返回结果
     * @param code 错误码
     * @param message 错误提示
     */
    public static <T> ResponseResult<T> failed(Integer code, String message) {
        return new ResponseResult<T>(code, message);
    }

    /**
     * 失败返回结果
     * @param message 错误提示
     */
    public static <T> ResponseResult<T> failed(String message) {
        return new ResponseResult<T>(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
