package com.cjkj.insurance.entity;

public class ScoreRate {
    /**
     * 业务评级
     */
    private Integer scoreRateId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
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
     * 商业险评分
     */
    private String bizScore;

    /**
     * 交强险评分
     */
    private String trafficScore;

    /**
     * 商业险评级
     */
    private String bizRate;

    /**
     * 交强险评级
     */
    private String trafficRate;

    /**
     * 业务评级
     * @return score_rate_id 业务评级
     */
    public Integer getScoreRateId() {
        return scoreRateId;
    }

    /**
     * 业务评级
     * @param scoreRateId 业务评级
     */
    public void setScoreRateId(Integer scoreRateId) {
        this.scoreRateId = scoreRateId;
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
     * 商业险评分
     * @return biz_score 商业险评分
     */
    public String getBizScore() {
        return bizScore;
    }

    /**
     * 商业险评分
     * @param bizScore 商业险评分
     */
    public void setBizScore(String bizScore) {
        this.bizScore = bizScore == null ? null : bizScore.trim();
    }

    /**
     * 交强险评分
     * @return traffic_score 交强险评分
     */
    public String getTrafficScore() {
        return trafficScore;
    }

    /**
     * 交强险评分
     * @param trafficScore 交强险评分
     */
    public void setTrafficScore(String trafficScore) {
        this.trafficScore = trafficScore == null ? null : trafficScore.trim();
    }

    /**
     * 商业险评级
     * @return biz_rate 商业险评级
     */
    public String getBizRate() {
        return bizRate;
    }

    /**
     * 商业险评级
     * @param bizRate 商业险评级
     */
    public void setBizRate(String bizRate) {
        this.bizRate = bizRate == null ? null : bizRate.trim();
    }

    /**
     * 交强险评级
     * @return traffic_rate 交强险评级
     */
    public String getTrafficRate() {
        return trafficRate;
    }

    /**
     * 交强险评级
     * @param trafficRate 交强险评级
     */
    public void setTrafficRate(String trafficRate) {
        this.trafficRate = trafficRate == null ? null : trafficRate.trim();
    }
}