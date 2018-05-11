package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.UserImg;

import java.util.List;

public interface UserImgMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer userImgId);

    /**
     *
     * @mbggenerated
     */
    int insert(UserImg record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(UserImg record);

    /**
     *
     * @mbggenerated
     */
    UserImg selectByPrimaryKey(Integer userImgId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserImg record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserImg record);

    /**
     * 批量添加影像信息
     */
    public int addUserImgs(List<UserImg> userImgs);

    /**
     * 根据任务号查询所有影像信息
     */
    public List<UserImg> findAllUserImg(String taskId);
}