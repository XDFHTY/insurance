package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关系人信息  订单详情用
 * Created by XD on 2018/5/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    /**
     * 信息类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     */
    private String msgTypeGxr;

    /**
     * 姓名
     */
    private String nameGxr;

    /**
     * 证件号
     */
    private String idcardNoGxr;
}
