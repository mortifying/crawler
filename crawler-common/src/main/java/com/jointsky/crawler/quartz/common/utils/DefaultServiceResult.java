package com.jointsky.crawler.quartz.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Author  zhangxiong
 * Date    17-7-27 下午3:30
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultServiceResult {

    private boolean isSuccess;
    private String error;
    private Integer code;
    private Object data;

    public DefaultServiceResult(boolean isSuccess, String error) {
        this.isSuccess = isSuccess;
        this.error = error;
    }

    public DefaultServiceResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public DefaultServiceResult(String error) {
        isSuccess = false;
        this.error = error;
    }

    public DefaultServiceResult(Object data) {
        isSuccess = true;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
