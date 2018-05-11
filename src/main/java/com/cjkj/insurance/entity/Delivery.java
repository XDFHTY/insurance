package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    /**
     * 配送信息
     */
    private Integer deliveryId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 0-自取，1-快递，3-电子保单
     */
    private String deliveryType;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 收件人手机号码
     */
    private String phone;

    /**
     * 配送地址省编码
     */
    private String province;

    /**
     * 配送地址市编码
     */
    private String city;

    /**
     * 配送地址区县编码
     */
    private String area;

    /**
     * 配送详细地址
     */
    private String address;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID
     */
    private String prvId;

    /**
     * 邮编
     */
    private String zip;

    /**
     * 快递公司名称
     */
    private String expressCompanyName;

    /**
     * 快递单号
     */
    private String expressNumber;

    /**
     * 商业险电子保单地址
     */
    private String syElecPolicyFilePath;


    /**
     * 交强险电子保单地址
     */
    private String jpElecPolicyFilePath;

    /**
     * 出单网点名称
     */
    private String outDept;


}