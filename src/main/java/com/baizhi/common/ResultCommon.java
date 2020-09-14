package com.baizhi.common;

/**
 * @ClassNmae: ResultCommon
 * @Author: yddm
 * @DateTime: 2020/9/1 11:06
 * @Description: TODO
 */

public class ResultCommon {
    private Object data;
    private String message;
    private String status;

    @Override
    public String toString() {
        return "ResultCommon{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultCommon(Object data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public ResultCommon() {
    }
}
