package com.cjkj.insurance.entity.other;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 上年交强险信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EfcInsureInfo {

    /**
     * 交强险起保日期
     */
    private Date startDate;


    /**
     * 交强险终保日期
     */
    private Date endDate;


    /**
     *交强险保额
     */
    private BigDecimal amount;

    /**
     *交强险保费
     */
    private BigDecimal premium;

    /**
     *承保成功后才有交强险保单号
     */
    private  String policyNo;

    /**
     * 交强险折扣率
     */
    private BigDecimal discountRate;

}
