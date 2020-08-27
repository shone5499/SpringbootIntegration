package com.shone.elasticsearchstudy.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 返回参数
 * @author xiaoguojian
 */
@Getter
@AllArgsConstructor
public enum ReturnEnum {

    /**
     * 请求成功
     */
    RETURN_SUCCESS_ENUM(200, "请求成功"),

    /**
     * 请求失败
     */
    RETURN_FAILURE_ENUM(300, "请求失败"),

    /**
     * 无权限或token过期
     */
    RETURN_NO_AUTH_ENUM(401, "无权限或token过期"),

    /**
     * 请求路径不存在
     */
    RETURN_NOT_FOUND_ENUM(404, "请求路径不存在"),

    /**
     * 参数传递错误
     */
    RETURN_PARAM_INVALID_ENUM(405, "参数传递错误"),

    /**
     * 系统异常
     */
    RETURN_ERROR_ENUM(500, "系统异常");



    private Integer code;
    private String msg;

    public static String getMsg(Integer value) {
        return Arrays.stream(values()).filter(q -> q.code.equals(value)).findFirst().map(ReturnEnum::getMsg).orElse(null);
    }

}
