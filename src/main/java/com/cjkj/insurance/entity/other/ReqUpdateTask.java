package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 修改报价请求信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateTask {

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID（可选，若填则只修改此供应商的投保信息）", //详见备注
     */
    private String prvId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 车辆信息
     */
    private CarInfo carInfo;

    /**
     * 险种信息
     */
    private InsureInfo insureInfo;

    /**
     *投保供应商
     */
    private List<Providers> providers;

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
     * 索赔权益人信息
     */
    private CarOwner beneficiary;

    /**
     * 配送信息
     */
    private Delivery delivery;

    /**
     * 发票信息
     */
    private Invoiceinfo invoiceInfo;

    /**
     * 核保补充数据项
     */
    private List<InsureSupplys> insureSupplys;



}
