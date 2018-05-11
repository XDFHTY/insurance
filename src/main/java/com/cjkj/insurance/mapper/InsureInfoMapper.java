package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.InsureInfo;

import java.util.List;
import java.util.Map;

public interface InsureInfoMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer insureInfoId);

    /**
     *
     * @mbggenerated
     */
    int insert(InsureInfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(InsureInfo record);

    /**
     *
     * @mbggenerated
     */
    InsureInfo selectByPrimaryKey(Integer insureInfoId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(InsureInfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(InsureInfo record);

    /**
     * 批量添加数据
     */
    public int addInsureInfos(List<InsureInfo> insureInfos);

    /**
     * 查询信息
     */
    public List<InsureInfo> findAllInsureInfoByMap(Map map);
}