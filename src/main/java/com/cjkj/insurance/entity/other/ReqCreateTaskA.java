package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.CarInfo;
import com.cjkj.insurance.entity.CarOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A接口创建报价请求信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqCreateTaskA {

    /**
     * 投保地区代码(市级代码)",  //例如广州市440100
     */
    private String insureAreaCode;

    /**
     *渠道用户ID",  //从渠道进来的第三方用户的一个标识
     */
    private String channelUserId;

    /**
     *车辆信息
     */
    private CarInfo carInfo;

    /**
     * 车主姓名
     */
    private CarOwner carOwner;
}
