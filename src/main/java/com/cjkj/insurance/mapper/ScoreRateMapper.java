package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.ScoreRate;

public interface ScoreRateMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer scoreRateId);

    /**
     *
     * @mbggenerated
     */
    int insert(ScoreRate record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ScoreRate record);

    /**
     *
     * @mbggenerated
     */
    ScoreRate selectByPrimaryKey(Integer scoreRateId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ScoreRate record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ScoreRate record);

    /**
     * 根据任务号查寻
     */
    public ScoreRate findScoreRateByTaskId(String taskId);
}