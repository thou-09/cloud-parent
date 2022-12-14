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
    EXAMINE_NOT_EXIST("B0004", "审核数据不存在"),
    DATA_HAS_EXAMINED("B0005", "数据已被审核"),
    SERVERINFO_NOT_EXIST("B0006", "服务数据不存在"),
    USER_HAS_BINDED_OTHER("B0007", "用户已绑定其他服务商"),
    USER_HAS_BINDED_OWN("B0007", "用户已被您绑定"),
    TYPE_NOT_EXIST("B0008", "类型数据不存在"),
    TYPE_HAS_RELATION("B0009", "当前类型有服务关联，不可删除"),
    NOTICE_NOT_EXIST("B0010", "通知数据不存在"),
    USER_ACCESS_NOT_ALLOWED("B0011", "用户没有权限"),

    USER_NAME_IS_BLACK("C0001", "用户名不可为空"),
    USER_PHONE_IS_BLACK("C0002", "手机号不可为空"),
    USER_PASSWORD_IS_BLACK("C0003", "密码不可为空"),
    USER_REPASSWORD_IS_BLACK("C0004", "确认密码不可为空"),
    USER_PASSWORD_NOT_EQUALS("C0005", "两次输入密码不一致"),
    USER_CHECKCODE_IS_BLACK("C0006", "验证码不可为空"),
    USER_CHECKCODE_IS_ERROR("C0007", "验证码不一致"),
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
