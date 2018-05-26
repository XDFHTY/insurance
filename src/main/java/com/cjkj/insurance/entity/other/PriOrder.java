package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 订单列表中   供应商信息 + 状态 + 车主信息
 * Created by XD on 2018/5/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriOrder {
    //创建时间
    private Date createTime;

    //车主姓名
    private String name;

    //信息类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
    private String msgType;

    //车牌
    private String carLicenseNo;

    //任务状态代码
    private String taskState;

    //任务状态描述
    private String taskStateDescription;

    //供应商ID
    private String prvId;

    //保险公司简称
    private String prvName;
}
