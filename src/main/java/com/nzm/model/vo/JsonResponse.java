package com.nzm.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nzm.model.enums.FeedBack;

/**
 * 返回结果包装类
 * Created by nzm on 2017/6/1.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponse<T> {
    /**
     * 是否成功返回
     */
    private boolean success;
    /**
     * 成功返回时，返回的数据
     */
    private T data;
    /**
     * 错误原因
     */
    private String message;

    public JsonResponse() {

    }

    /**
     * 错误返回
     *
     * @param feedBack 反馈枚举类
     * @return 当前JsonResponse对象
     */
    public JsonResponse<T> createError(FeedBack feedBack) {
        this.success = false;
        this.message = feedBack.getStatus();
        return this;
    }

    public JsonResponse<T> createError(String message) {
        this.success = false;
        this.message = message;
        return this;
    }

    public JsonResponse<T> createError(String message, T data) {
        this.success = false;
        this.message = message;
        this.data = data;
        return this;
    }


    /**
     * 成功返回
     *
     * @param data 返回的数据
     * @return 当前JsonResponse对象
     */
    public JsonResponse<T> createSuccess(T data) {
        this.success = true;
        this.data = data;
        return this;
    }

    public JsonResponse<T> createSuccess(String message, T data) {
        this.success = true;
        this.message = message;
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
