package com.cjkj.insurance.utils;

import com.google.gson.Gson;

/**
 * Created by XD on 2018/4/4.
 */
public class BeanToJsonUtil {
    /**
     * 将对象转json
     * @param bean
     * @return
     */
    public static String beanToJSONString(Object bean) {
        return new Gson().toJson(bean);
    }
}
