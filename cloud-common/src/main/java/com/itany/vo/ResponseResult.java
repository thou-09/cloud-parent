package com.itany.vo;

import com.itany.exception.AppException;

import java.io.Serializable;

/**
 * 自定义响应对象
 *
 * @author Thou
 * @date 2022/10/18
 */
@SuppressWarnings("unused")
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1997384422946860831L;
    /**
     * 响应状态码
     */
    private String status;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    private ResponseResult() {}

    private ResponseResult(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>("00000", "成功", data);
    }

    public static <T> ResponseResult<T> fail(AppException e) {
        return new ResponseResult<>(e.getCode(), e.getMessage(), null);
    }

    public static <T> ResponseResult<T> fail(String status, String message) {
        return new ResponseResult<>(status, message, null);
    }
}
