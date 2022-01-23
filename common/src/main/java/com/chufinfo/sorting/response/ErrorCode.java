package com.chufinfo.sorting.response;

public enum ErrorCode {
    Login_out(10000,"token失效，请重新登陆"),
    BUSINESS_ERROR(10001,"业务错误"),
    NETWORK_ERROR(10002,"网络错误"),
    SYSTEM_ERROR(10003,"系统繁忙"),
    DB_ERROR(10004,"数据库错误"),
    UNKNOWN_ERROR(10005,"未知错误"),
	PASSWORD_ERROR(10006,"用户名密码错误"),
    AUTH_FAILED(10007,"鉴权失败"),
    REMOTE_ERROR(10009,"远程服务错误"),
    NAME_EXIST(10010,"名称已存在"),
    INVALID_VIP(10011,"不是会员"),
    HAVE_VERIFY(10015,"已经完成认证"),
    TOKEN_ERROR(10016,"TOKEN错误"),
    NOT_LOGIN(10017,"未登陆"),
    USER_STOP(10018,"账号停用");

    private Integer code;
    private String tip;
    ErrorCode(Integer code, String tip){
        this.code=code;
        this.tip=tip;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
