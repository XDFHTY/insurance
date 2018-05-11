package com.cjkj.insurance.entity;

import java.util.Date;

public class UserImg {
    /**
     * 用户图片表
     */
    private Integer userImgId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-id
     */
    private Integer carInfoId;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID
     */
    private String prvId;

    /**
     * 图片类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     */
    private String userType;

    /**
     * 图片类型
     */
    private String imgType;

    /**
     * 影像描述
     */
    private String imgName;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户图片表
     * @return user_img_id 用户图片表
     */
    public Integer getUserImgId() {
        return userImgId;
    }

    /**
     * 用户图片表
     * @param userImgId 用户图片表
     */
    public void setUserImgId(Integer userImgId) {
        this.userImgId = userImgId;
    }

    /**
     * 用户ID
     * @return user_id 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * car_info-id
     * @return car_info_id car_info-id
     */
    public Integer getCarInfoId() {
        return carInfoId;
    }

    /**
     * car_info-id
     * @param carInfoId car_info-id
     */
    public void setCarInfoId(Integer carInfoId) {
        this.carInfoId = carInfoId;
    }

    /**
     * 任务号
     * @return task_id 任务号
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 任务号
     * @param taskId 任务号
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 供应商ID
     * @return prv_id 供应商ID
     */
    public String getPrvId() {
        return prvId;
    }

    /**
     * 供应商ID
     * @param prvId 供应商ID
     */
    public void setPrvId(String prvId) {
        this.prvId = prvId == null ? null : prvId.trim();
    }

    /**
     * 图片类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     * @return user_type 图片类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 图片类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     * @param userType 图片类型，1-车主，2-投保人，3-被保人，4-索赔权益人信息
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 图片类型
     * @return img_type 图片类型
     */
    public String getImgType() {
        return imgType;
    }

    /**
     * 图片类型
     * @param imgType 图片类型
     */
    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

    /**
     * 影像描述
     * @return img_name 影像描述
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 影像描述
     * @param imgName 影像描述
     */
    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    /**
     * 图片地址
     * @return img_url 图片地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 图片地址
     * @param imgUrl 图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}