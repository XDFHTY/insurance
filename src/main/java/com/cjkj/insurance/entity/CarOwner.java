package com.cjkj.insurance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarOwner {
    /**
     * 车主信息表
     */
    private Integer carOwnerId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 信息类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     */
    private String msgType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件类型
     */
    private String idcardType;

    /**
     * 证件号
     */
    private String idcardNo;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 手机号码
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 相应信息ID
     */
    private Integer respMsgId;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商Id
     */
    private String prvId;


}