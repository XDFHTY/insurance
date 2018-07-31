package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {


    //用户ID
    private Long userId;
    //任务号
    private String taskId;
    //供应商ID
    private String prvId;
    //供应商名称
    private String prvName;
    //任务状态
    private String taskState;
    //创建时间
    private Date createTime;
    //车牌号
    private String carLicenseNo;
    //车主姓名
    private String carOwnerName;


}
