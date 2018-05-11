package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.RiskKinds;

import java.util.List;
import java.util.Map;

public interface RiskKindsMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer riskKindsId);

    /**
     *
     * @mbggenerated
     */
    int insert(RiskKinds record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RiskKinds record);

    /**
     *
     * @mbggenerated
     */
    RiskKinds selectByPrimaryKey(Integer riskKindsId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RiskKinds record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RiskKinds record);

    /**
     * 批量添加信息
     */
    public int addRiskKinds(List<RiskKinds> riskKinds);

    /**
     * 查询信息
     */
    public List<RiskKinds> findAllRiskKindsByTaskId(Map map);
}