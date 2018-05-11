package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

/**
 * 回调接口
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespFinalState {

    //任务号
    private String taskId;

    //供应商ID
    private String prvId;

    //保险公司简称
    private String prvName;

    //回调结果，00：成功，01：失败或异常
    private String respCode;

    //错误消息以及备注信息
    private String errorMsg;

    //渠道id
    private String channelId;

    //渠道用户id
    private String channelUserId;

    //任务状态代码
    private String taskState;

    //任务状态描述
    private String taskStateDescription;

    //车辆信息
    private CarInfo carInfo;

    //车主信息
    private CarOwner carOwner;

    //保险配置
    private InsureInfo insureInfo;

    //01-上传影像提醒，详见下方影像规则说明
    private String msgType;

    //影像规则说明
    private List<ImageInfo> imageInfos;

    //验车码
    private String inspectionCode;

    //配送信息
    private Delivery delivery;

    //报价有效期
    private String quoteValidTime;

    //支付有效期
    private String payValidTime;

    //任务状态描述
    private List<InsureSupplys> insureSupplys;

    //业务评级
    private ScoreRate scoreRate;
}
