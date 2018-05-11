package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.Delivery;

public interface DeliveryMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer deliveryId);

    /**
     *
     * @mbggenerated
     */
    int insert(Delivery record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Delivery record);

    /**
     *
     * @mbggenerated
     */
    Delivery selectByPrimaryKey(Integer deliveryId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Delivery record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Delivery record);

    /**
     * 间距任务号查询信息
     *
     */
    public Delivery findDeliveryByTaskId(String taskId);
}