package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单详情信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

    //用户ID
    private Long userId;
    //任务号
    private String taskId;
    //供应商Id
    private String prvId;
    //状态码
    private String taskState;

    //请求回调信息
    private RespMsg respMsg;

    //车辆信息
    private CarInfo carInfo;

    //车主、投保人、被保人、索赔人
    private List<CarOwner> carOwners;

    //影像信息
    private List<UserImg> userImgs;

    //配送信息
    private Delivery delivery;

    //险种信息（商业险、车船税、交强险）
    private List<InsureInfo> insureInfos;

    //核保补充数据项
    private List<InsureSupplys> insureSupplys;

    //业务评级
    private ScoreRate scoreRate;



}
