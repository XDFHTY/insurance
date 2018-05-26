package com.cjkj.insurance.entity.other;

import com.cjkj.insurance.entity.CarModelinfos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单详情实体类
 * Created by XD on 2018/5/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespOrderItme extends RespFinalState {
    //关系人信息
    private List<Participant> participantList;

    //车型信息
    private CarModelinfos carModelinfos;
}
