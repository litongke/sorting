package com.chufinfo.sorting.response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerResponseEntity {

    private static Integer success = 1;
    private static Integer failed = -1;

    public static <T> JsonObj success(T data) {
        JsonObj JsonObj = new JsonObj();
        JsonObj.setData(data);
        JsonObj.setCode(success);
        return JsonObj;
    }

    public static <T> JsonObj success() {
        JsonObj JsonObj = new JsonObj();
        JsonObj.setCode(success);
        return JsonObj;
    }

    public static <T> JsonObj fail(String msg) {
        log.error(msg);
        JsonObj JsonObj = new JsonObj();
        JsonObj.setMsg(msg);
        JsonObj.setCode(failed);
        return JsonObj;
    }

    public static <T> JsonObj fail(String msg, T data) {
        log.error(msg);
        JsonObj JsonObj = new JsonObj();
        JsonObj.setMsg(msg);
        JsonObj.setCode(failed);
        JsonObj.setData(data);
        return JsonObj;
    }

    public static <T> JsonObj fail(int code ,String msg, T data) {
        log.error(msg);
        JsonObj JsonObj = new JsonObj();
        JsonObj.setMsg(msg);
        JsonObj.setCode(code);
        JsonObj.setData(data);
        return JsonObj;
    }
}
