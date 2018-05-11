package com.cjkj.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqMsg {
    /**
     * 需存储的请求信息
     */
    private Integer reqMsgId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 1-A接口创建报价，2-B接口创建报价，3-修改报价，4-提交报价任务
     */
    private String reqType;

    /**
     * "投保地区代码(市级代码)",  //例如广州市440100
     */
    private String insureAreaCode;

    /**
     * "渠道用户ID",  //从渠道进来的第三方用户的一个标识
     */
    private String channeiUserId;

    /**
     * 任务号
     */
    private String taskid;

    /**
     * 供应商ID
     */
    private String prvid;

    /**
     * 备注
     */
    private String remark;


}