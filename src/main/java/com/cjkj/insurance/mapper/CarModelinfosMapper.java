package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.CarModelinfos;

public interface CarModelinfosMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer carModelinfosId);

    /**
     *
     * @mbggenerated
     */
    int insert(CarModelinfos record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(CarModelinfos record);

    /**
     *
     * @mbggenerated
     */
    CarModelinfos selectByPrimaryKey(Integer carModelinfosId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CarModelinfos record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CarModelinfos record);
}