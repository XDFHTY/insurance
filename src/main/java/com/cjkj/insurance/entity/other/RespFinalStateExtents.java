package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 任务号实体类继承
 * Created by XD on 2018/5/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespFinalStateExtents extends RespFinalState {
    //供应商信息 + 车主信息
    private List<PriOrder> priOrderList;
}
