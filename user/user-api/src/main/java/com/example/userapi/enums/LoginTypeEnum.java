package com.example.userapi.enums;

public enum LoginTypeEnum {


    /**
     * 定义登录方式
     */
    AccountAndPass("账号密码", 0),
    Message("短信登录", 1),
    Weixin("微信登录", 2);

    private final String text;

    private final int value;

    LoginTypeEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
