package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.CarInfo;

import java.util.List;

public interface CarInfoMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer carInfoId);

    /**
     *
     * @mbggenerated
     */
    int insert(CarInfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(CarInfo record);

    /**
     *
     * @mbggenerated
     */
    CarInfo selectByPrimaryKey(Integer carInfoId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CarInfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CarInfo record);

    /**
     * 更新任务号
     */
    public int updateCarinfo(CarInfo carInfo);

    /**
     * 根据任务号查询信息
     */
    public CarInfo findCarInfo(String taskId);

    //检查 此用户 此车牌 是否已存在
    public List<CarInfo> findCarInfos(CarInfo carInfo);
}