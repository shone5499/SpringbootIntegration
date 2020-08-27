package com.shone.druidstudy.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 通用返回结果
 * @author xiaoguojian
 */

@ApiModel(description = "返回对象")
public class MyResponse<T> implements Serializable {

    @ApiModelProperty(value = "返回状态码",example = "200")
    private Integer code;

    @ApiModelProperty(value = "返回消息",example = "请求成功")
    private String msg;

    @ApiModelProperty(value = "返回数据对象",example = "{}")
    private T data;

    public MyResponse() {
        this(ReturnEnum.RETURN_SUCCESS_ENUM);
    }

    public MyResponse(T data) {
        this(ReturnEnum.RETURN_SUCCESS_ENUM);
        this.data = data;
    }

    public MyResponse(ReturnEnum returnEnum) {
        this.code = returnEnum.getCode();
        this.msg = returnEnum.getMsg();
    }

    public MyResponse(ReturnEnum returnEnum, T data) {
        this.code = returnEnum.getCode();
        this.msg = returnEnum.getMsg();
        this.data = data;
    }

    public MyResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public MyResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
