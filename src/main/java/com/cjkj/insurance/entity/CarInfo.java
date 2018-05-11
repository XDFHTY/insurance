package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarInfo {
    /**
     * 车主-车辆信息表
     */
    private Integer carInfoId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 是否未上牌,  // 当isNew=Y，新车发票价必传
     */
    private String isNew;

    /**
     * 新车发票价, //新车未上牌或者初登日期小于9个月，此参数必传
     */
    private BigDecimal price;

    /**
     * 车牌号,  //当isNew=Y时，此参数不传
     */
    private String carLicenseNo;

    /**
     * 车架号
     */
    private String vinCode;

    /**
     * 发动机号
     */
    private String engineNo;

    /**
     * "车辆使用性质",  （默认：家庭自用汽车）
     */
    private String carProperty;

    /**
     * 车辆所属性质",     （默认：个人用车）
     */
    private String property;

    /**
     * 初登日期
     */
    private String registDate;

    /**
     * 是否过户车",  //当isTransferCar=Y时，过户日期必传
     */
    private String isTransferCar;

    /**
     *  "过户日期",  //当isTransferCar=Y时，此参数必传
     */
    private String transferDate;

    /**
     * "车型名称"
     */
    private String vehicleName;

    /**
     * 车型id
     */
    private String vehicleId;

    /**
     * 核定载重量(货车必填)
     */
    private String modelLoads;

    /**
     * 车辆用途
     */
    private String purpose;

    /**
     * 核定载客人数
     */
    private Integer seat;

    /**
     * 整备质量
     */
    private Float fullWeight;

    /**
     * 排气量
     */
    private Float displacement;

    /**
     * 行驶区域
     */
    private String drivingArea;

    /**
     * 响应信息ID
     */
    private Integer respMsgId;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 投保供应商ID
     */
    private String prvId;

    /**
     * 验车码
     */
    private String inspectionCode;

    /**
     * 报价有效期
     */
    private Date quoteValidTime;

    /**
     * 支付有效期
     */
    private Date payValidTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}