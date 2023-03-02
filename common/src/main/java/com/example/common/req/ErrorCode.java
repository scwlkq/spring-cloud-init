package com.example.common.req;

/**
 * 错误码
 *
 * @author yupi
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    USER_NAME_ERROR(40001, "账户中存在特殊字符", ""),
    PASSWROD_NOT_MATCH_ERROR(40002, "两次输入的密码不一致", ""),
    USER_NAME_SAME(40003, "账户重复", ""),
    USER_NOT_FOUND(40004, "没有找到该用户", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),
    SAVE_ERROR(50010, "新增失败", ""),
    UPDATE_ERROR(50020, "更新失败", ""),
    TIMEOUT_ERROR(50030, "超时触发熔断", "请过会再来请求"),

    DELETE_ERROR(50040, "删除失败", "");

    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
