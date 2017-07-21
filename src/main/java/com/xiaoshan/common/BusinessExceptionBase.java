package com.xiaoshan.common;

/**
 * Created by gukc007 on 2017-05-07.
 */
public class BusinessExceptionBase extends Exception{

    /**
     * 抛出业务异常
     */
    public BusinessExceptionBase(EnumExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    /**
     * 抛出业务异常
     */
    public BusinessExceptionBase(EnumExceptionType type, String arg1) {
        super(String.format(type.getMessage(), arg1));
        this.type = type;
        this.arguments = new String[]{arg1};
    }

    public BusinessExceptionBase(EnumExceptionType type, String arg1, String arg2) {
        super(String.format(type.getMessage(), arg1, arg2));
        this.type = type;
        this.arguments = new String[]{arg1, arg2};
    }

    public BusinessExceptionBase(EnumExceptionType type, String arg1, String arg2, String arg3) {
        super(String.format(type.getMessage(), arg1, arg2, arg3));
        this.type = type;
        this.arguments = new String[]{arg1, arg2, arg3};
    }

    /**
     * 异常类型
     */
    private EnumExceptionType type;

    private String[] arguments;

    public String getCode() {
        return type.getCode();
    }

    public String getMessage() {
        if (arguments != null) {
            return String.format(type.getMessage(), (Object[]) arguments);
        }
        return type.getMessage();
    }

    public EnumExceptionType getType() {
        return type;
    }

    public void setType(EnumExceptionType type) {
        this.type = type;
    }

    public enum EnumExceptionType {

        UserNoRight("401", "Token失效"),
        UserAccountHasExist("10001", "帐号已存在"),
        UserNotExist("10002", "用户不存在"),
        UserNameOrPasswordError("10003", "用户名或密码错误");

        private String code;
        private String message;

        EnumExceptionType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
