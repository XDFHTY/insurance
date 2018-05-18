package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.DqLicenceLocation;

public interface DqLicenceLocationMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(DqLicenceLocation record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(DqLicenceLocation record);

    /**
     *
     * @mbggenerated
     */
    DqLicenceLocation selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DqLicenceLocation record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DqLicenceLocation record);

    //查询车牌前缀
    public String findLicensePlateByCityId(String cityId);
}