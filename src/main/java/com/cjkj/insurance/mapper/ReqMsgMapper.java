package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.ReqMsg;

public interface ReqMsgMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer reqMsgId);

    /**
     *
     * @mbggenerated
     */
    int insert(ReqMsg record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ReqMsg record);

    /**
     *
     * @mbggenerated
     */
    ReqMsg selectByPrimaryKey(Integer reqMsgId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ReqMsg record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReqMsg record);
}