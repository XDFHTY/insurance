package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 车船税信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxInsureInfo {


    /**
     * 是否代缴车船税
     */
    private String isPaymentTax;

    /**
     * 车船税金额
     */
    private BigDecimal taxFee;

    /**
     * 车船税滞纳金
     */
    private BigDecimal lateFee;
}
