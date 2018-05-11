package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskKinds {
    /**
     * 商业险险种信息
     */
    private Integer riskKindsId;

    /**
     * 险种信息表ID
     */
    private Integer insureinfoId;

    /**
     * 险别代码
     */
    private String riskCode;

    /**
     * 险种名称
     */
    private String riskName;

    /**
     * 险种保额
     */
    private BigDecimal amount;

    /**
     * 是否投保不计免赔
     */
    private String notDeductible;

    /**
     * 险种保费
     */
    private BigDecimal premium;

    /**
     * 险种不计免赔保费
     */
    private BigDecimal ncfPremium;

    /**
     * 相应信息ID
     */
    private Integer respMsgId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID
     */
    private String prvId;


}