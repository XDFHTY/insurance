package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.CarInfo;
import com.cjkj.insurance.entity.CarOwner;
import com.cjkj.insurance.entity.InsureInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * B接口创建报价请求数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqCreateTaskB {

    /**
     * 投保地区代码(市级代码)",  //例如广州市440100
     */
    private String insureAreaCode;

    /**
     * 渠道用户ID",  //从渠道进来的第三方用户的一个标识
     */
    private String channelUserId;


    /**
     * 备注
     */
    private String remark;

    /**
     * 车辆信息
     */
    private CarInfo carInfo;

    /**
     * 车主信息
     */
    private CarOwner carOwner;

    /**
     * 投保人信息
     */
    private CarOwner applicant;

    /**
     * 被保人信息
     */
    private CarOwner insured;

    /**
     * 险种信息
     */
    private InsureInfo insureInfo;

    /**
     * 供应商id集合
     */
    private List<Providers>  providers;


}
