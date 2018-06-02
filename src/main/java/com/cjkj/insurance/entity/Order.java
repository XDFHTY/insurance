package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 用户订单列表
 * Created by XD on 2018/6/2.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    //车牌
    private String carLicenseNo;
    //任务号
    private String taskId;
    //车主姓名
    private String name;
    //有效时间
    private Date quoteValidTime;

    //保险信息
    List<RespMsg> list;

}
