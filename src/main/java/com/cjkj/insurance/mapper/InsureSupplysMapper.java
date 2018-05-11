package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.InsureSupplys;

import java.util.List;

public interface InsureSupplysMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer insureSupplysId);

    /**
     *
     * @mbggenerated
     */
    int insert(InsureSupplys record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(InsureSupplys record);

    /**
     *
     * @mbggenerated
     */
    InsureSupplys selectByPrimaryKey(Integer insureSupplysId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(InsureSupplys record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(InsureSupplys record);

    /**
     * 批量添加
     */
    public int addInsureSupplys(List<InsureSupplys> insureSupplys);

    /**
     * 根据任务号查询
     */
    public List<InsureSupplys> findAllInsureSupplysByTaskId(String taskId);
}