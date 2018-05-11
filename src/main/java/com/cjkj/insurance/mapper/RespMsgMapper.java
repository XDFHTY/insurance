package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.RespMsg;

import java.util.Map;

public interface RespMsgMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer respMsgId);

    /**
     *
     * @mbggenerated
     */
    int insert(RespMsg record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RespMsg record);

    /**
     *
     * @mbggenerated
     */
    RespMsg selectByPrimaryKey(Integer respMsgId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RespMsg record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RespMsg record);

    /**
     * 根据任务号查询信息
     */
    public RespMsg findRespMsgByTaskId(Map map);


}