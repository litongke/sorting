package com.chufinfo.sorting.enums;

/**
 * 分拣系统枚举
 * 
 * @author litk
 */
public class SystemEnum {
    public enum  gzStatus{
        END(0, "未开始"), START(1, "开始");
        private  Integer code;
        private  String message;

        gzStatus(Integer code, String message)
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
    public enum  userLevel{
        ADMIN(1,"管理员"),FJRY(2,"分拣员"),JJRY(3,"交接员");
        private   Integer code;
        private  String message;
        userLevel(Integer code,String message){
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
    public enum  delFlag{
        OK(0, "未删除"), DELETE(1, "删除"), LOCKED(2, "锁定");
        private  Integer code;
        private  String message;

        delFlag(Integer code, String message)
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
    public enum  usingStatus{
        YES(0, "是"), NO(1, "否");
        private  Integer code;
        private  String message;

        usingStatus(Integer code, String message)
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
    public enum  sgjStatus{
        XJSGJ(1,"下架"),SJSGJ(0,"上架"),JJSGJ(2,"已交接");
        private   Integer code;
        private  String message;
        sgjStatus(Integer code,String message){
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
    public enum  bbStatus{
        WJJ(0,"未交接"),YJJ(1,"已交接");
        private   Integer code;
        private  String message;
        bbStatus(Integer code,String message){
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

}
