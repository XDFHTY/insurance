package com.cjkj.insurance.entity;

public class InsureSupplys {
    /**
     * 核保补充数据项
     */
    private Integer insureSupplysId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 数据项编码
     */
    private String itemCode;

    /**
     * 数据项值
     */
    private String itemValue;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID
     */
    private String prvId;

    /**
     * 数据项录入类型
     */
    private String itemInputType;

    /**
     * 当iteminputtype为select时，才有itemoptions
     */
    private String itemOptions;

    /**
     * 核保补充数据项
     * @return insure_supplys_id 核保补充数据项
     */
    public Integer getInsureSupplysId() {
        return insureSupplysId;
    }

    /**
     * 核保补充数据项
     * @param insureSupplysId 核保补充数据项
     */
    public void setInsureSupplysId(Integer insureSupplysId) {
        this.insureSupplysId = insureSupplysId;
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
     * car_info-ID
     * @return car_info_id car_info-ID
     */
    public Integer getCarInfoId() {
        return carInfoId;
    }

    /**
     * car_info-ID
     * @param carInfoId car_info-ID
     */
    public void setCarInfoId(Integer carInfoId) {
        this.carInfoId = carInfoId;
    }

    /**
     * 数据项编码
     * @return item_code 数据项编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 数据项编码
     * @param itemCode 数据项编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * 数据项值
     * @return item_value 数据项值
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 数据项值
     * @param itemValue 数据项值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
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
     * 数据项录入类型
     * @return item_input_type 数据项录入类型
     */
    public String getItemInputType() {
        return itemInputType;
    }

    /**
     * 数据项录入类型
     * @param itemInputType 数据项录入类型
     */
    public void setItemInputType(String itemInputType) {
        this.itemInputType = itemInputType == null ? null : itemInputType.trim();
    }

    /**
     * 当iteminputtype为select时，才有itemoptions
     * @return item_options 当iteminputtype为select时，才有itemoptions
     */
    public String getItemOptions() {
        return itemOptions;
    }

    /**
     * 当iteminputtype为select时，才有itemoptions
     * @param itemOptions 当iteminputtype为select时，才有itemoptions
     */
    public void setItemOptions(String itemOptions) {
        this.itemOptions = itemOptions == null ? null : itemOptions.trim();
    }
}