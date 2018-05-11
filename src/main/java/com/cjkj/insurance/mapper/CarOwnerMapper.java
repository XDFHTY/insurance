package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.CarOwner;

import java.util.List;
import java.util.Map;

public interface CarOwnerMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer carOwnerId);

    /**
     *
     * @mbggenerated
     */
    int insert(CarOwner record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(CarOwner record);

    /**
     *
     * @mbggenerated
     */
    CarOwner selectByPrimaryKey(Integer carOwnerId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CarOwner record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CarOwner record);

    /**
     * 批量添加信息
     */
    public int addCarOwner(List<CarOwner> ownerList);

    /**
     * 根据carInfoId 和任务号查询 车主信息
     */
    public CarOwner findCarOwner(Map map);

    /**
     * 根据carInfoId 和 userID更新用户信息任务号
     */
    public int updateCarOwner(CarOwner carOwner);
}