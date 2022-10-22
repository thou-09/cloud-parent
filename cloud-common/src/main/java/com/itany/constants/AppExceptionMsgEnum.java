package com.itany.constants;

/**
 * 自定义异常信息状态码枚举类
 *
 * @author Thou
 * @date 2022/10/19
 */
public enum AppExceptionMsgEnum {

    /**
     * 响应状态码和信息
     */
    FILE_UPLOAD_ERROR("A0001", "文件上传失败"),
    DATE_PARSE_ERROR("A0002", "日期格式错误"),

    USERNAME_AND_PASSWORD_ERROR("B0001", "用户名或密码错误"),
    USER_NOT_EXIST("B0002", "用户不存在"),
    SERVER_COMPANY_NOT_EXIST("B0003", "服务商不存在"),
    ;

    /**
     * 状态码
     */
    private final String statusCode;

    /**
     * 提示信息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param statusCode 状态码
     * @param message 提示信息
     * @author Thou
     * @date 2022/10/19
     */
    AppExceptionMsgEnum(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 获取状态码
     *
     * @return java.lang.String
     * @author Thou
     * @date 2022/10/19
     */
    public String getCode() {
        return statusCode;
    }

    /**
     * 获取提示信息
     *
     * @return java.lang.String
     * @author Thou
     * @date 2022/10/19
     */
    public String getMessage() {
        return message;
    }
}
