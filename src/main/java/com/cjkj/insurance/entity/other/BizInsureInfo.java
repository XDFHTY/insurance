package com.cjkj.insurance.entity.other;


import com.cjkj.insurance.entity.RiskKinds;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 上年商业险信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BizInsureInfo {

    /**
     * 商业险起保日期
     */
    private Date startDate;

    /**
     * 商业险终保日期
     */
    private Date endDate;

    /**
     * 商业险保费合计=商业险保费+商业险不计免赔保费
     */
    private BigDecimal premium;

    /**
     * 承保成功后才有商业险折扣率
     */
    private BigDecimal discountRate;

    /**
     * 商业险保单号
     */
    private String policyNo;

    /**
     * 商业险不计免赔保费
     */
    private BigDecimal nfcPremium;


    /**
     * 上年商业险险种信息集合
     */
    private List<RiskKinds> riskKinds;


}
