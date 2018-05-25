package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespMsg {
    /**
     * 需要记录的回调信息
     */
    private Integer respMsgId;

    /**
     * 用户ID
     */
    private Integer userId;

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

    /**
     * 保险公司简称
     */
    private String prvName;

    /**
     * 回调结果，00：成功，01：失败或异常
     */
    private String respCode;

    /**
     * 错误消息以及备注信息
     */
    private String errorMsg;

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 渠道用户id
     */
    private String channelUserId;

    /**
     * 任务状态代码
     */
    private String taskState;

    /**
     * 完成",  //任务状态描述
     */
    private String taskStateDescription;

    /**
     * 01-上传影像提醒，详见下方影像规则说明
     */
    private String msgType;

    /**
     * 验车码
     */
    private String inspectionCode;

    /**
     * 报价有效期
     */
    private Date quoteValidTime;

    /**
     * 支付有效期
     */
    private Date payValidTime;

    //回调的json串
    private String respJson;


}