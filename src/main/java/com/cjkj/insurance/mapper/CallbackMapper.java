package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.other.RespFinalState;

import java.util.List;
import java.util.Map;

public interface CallbackMapper {


    //查询所有的报价信息
    public List<Map> findAllCallback(Map map);


}
