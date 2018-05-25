package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    /**
     * 车辆信息
     */
    private List<CarInfo> carInfoList;

    /**
     * 车主信息
     */
    private List<CarOwner> carOwner;

    /**
     * 回掉信息
     */
    private List<RespMsg> respMsg;

    /**
     * 保险配置
     */
    private List<InsureInfo> insureInfo;

    /**
     *险种信息
     */
    private List<RiskKinds> riskKindsList;

    /**
     *用户影像信息
     */
    private List<UserImg> userImgList;

    /**
     *配送信息
     */
    private List<Delivery> deliveryList;

    /**
     *核保补充数据项
     */
    private List<InsureSupplys> supplysList;

    /**
     *业务评级信息
     */
    private List<ScoreRate> scoreRateList;

}

