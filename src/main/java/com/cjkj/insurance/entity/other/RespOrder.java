package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.CarOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 订单列表 返回实体类
 * Created by XD on 2018/5/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespOrder {
    //用户ID
    private Integer userId;

    //任务号信息 和 车主信息
    private List<RespFinalStateExtents> respFinalStateList;


}
