package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.CarOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 影像识别返回信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespImgDistinguish {

    /**
     * 状态码
     */
    private String respCode;

    /**
     * 行驶证识别信息
     */
    private DrivingInfo drivingInfo;

    /**
     * 身份证识别信息
     */
    private PersonInfo personInfo;


}
