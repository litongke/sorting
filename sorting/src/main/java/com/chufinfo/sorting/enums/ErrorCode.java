package com.chufinfo.sorting.enums;

public enum ErrorCode {

    UNKNOWN_ERROR("ERROR-00", "未知异常", "任务执行中出现未知异常，请确认任务配置。"),
    UNFINISHED_ERROR("ERROR-01", "运行终断", "任务未完成即终断,等待下一次重试，请仔细检查数据类型和网络连接。");

    private final String code;
    private final String name;
    private final String describe;

    ErrorCode(String code, String name, String describe) {
        this.code = code;
        this.name = name;
        this.describe = describe;
    }

    public String getCode() {
        return this.code;
    }
    public String getName() {return name;}
    public String getDescription() {
        return this.describe;
    }

    public String toString() {
        return String.format("Code:[%s], Name:[%s], Describe:[%s]", this.code, this.name, this.describe);
    }
}
