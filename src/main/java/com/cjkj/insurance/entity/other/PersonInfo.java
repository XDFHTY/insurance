package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件号码
     */
    private String idcardNo;
    /**
     * 性别
     */
    private String gender;
    /**
     * 民族
     */
    private String folk;
    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 地址
     */
    private String address;
    /**
     * 签发单位
     */
    private String issueAuthority;
    /**
     * 有效期
     */
    private String validPeriod;





}
