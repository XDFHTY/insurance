package com.cjkj.insurance.entity;

public class DqLicenceLocation {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 城市编号
     */
    private String cityId;

    /**
     * 车牌归属地
     */
    private String licenceLocation;

    /**
     * 自增主键
     * @return id 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增主键
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 城市编号
     * @return city_id 城市编号
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 城市编号
     * @param cityId 城市编号
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     * 车牌归属地
     * @return licence_location 车牌归属地
     */
    public String getLicenceLocation() {
        return licenceLocation;
    }

    /**
     * 车牌归属地
     * @param licenceLocation 车牌归属地
     */
    public void setLicenceLocation(String licenceLocation) {
        this.licenceLocation = licenceLocation == null ? null : licenceLocation.trim();
    }
}