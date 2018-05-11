package com.cjkj.insurance.entity;

import com.cjkj.insurance.entity.other.BizInsureInfo;
import com.cjkj.insurance.entity.other.EfcInsureInfo;
import com.cjkj.insurance.entity.other.TaxInsureInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsureInfo {
    /**
     * 险种信息表
     */
    private Integer insureInfoId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 1-交强险，2-车船税，3-商业险
     */
    private String msgType;

    /**
     * 起保日期
     */
    private Date startDate;

    /**
     * 终保日期
     */
    private Date endDate;

    /**
     * 交强险保额
     */
    private BigDecimal amount;

    /**
     * 交强险保费/商业险保费合计=商业险保费+商业险不计免赔保费
     */
    private BigDecimal premium;

    /**
     * 承保成功后才有交强险保单号/承保成功后才有商业险折扣率
     */
    private String policyNo;

    /**
     * 交强险折扣率/承保成功后才有商业险折扣率
     */
    private BigDecimal discountRate;

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

    /**
     * 商业险不计免赔保费
     */
    private BigDecimal nfcPremium;

    /**
     * 总保费
     */
    private BigDecimal totalPremium;

    /**
     * 响应信息ID
     */
    private Integer respMsgId;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID
     */
    private String prvId;

    private EfcInsureInfo efcInsureInfo;
    private TaxInsureInfo taxInsureInfo;
    private BizInsureInfo bizInsureInfo;


}