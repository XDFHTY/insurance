package com.cjkj.insurance.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息描述
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;


    /**
     * 其他
     */
    protected Object params;




}