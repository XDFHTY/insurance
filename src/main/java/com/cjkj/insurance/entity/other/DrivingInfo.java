package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrivingInfo {

    /**
     *车牌号
     */
    private String carLicenseNo;


    /**
     *车主
     */
    private String name;


    /**
     *行驶证地址
     */
    private String address;


    /**
     *车型
     */
    private String vehicleType;


    /**
     *使用性质
     */
    private String carProperty;


    /**
     *车型名称
     */
    private String vehicleName;


    /**
     *VIN码
     */
    private String vinCode;


    /**
     *发动机号
     */
    private String engineNo;


    /**
     *初登日期
     */
    private String registDate;

    /**
     *发证日期
     */
    private String issuedate;

}
