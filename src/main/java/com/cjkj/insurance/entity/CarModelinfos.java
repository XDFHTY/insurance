package com.cjkj.insurance.entity;

import java.math.BigDecimal;

public class CarModelinfos {
    /**
     * 车型信息
     */
    private Integer carModelinfosId;

    /**
     * 车型ID
     */
    private Integer vehicleId;

    /**
     * 上市年月
     */
    private String maketDate;

    /**
     * 新车购置价
     */
    private BigDecimal price;

    /**
     * 座位数
     */
    private Integer seat;

    /**
     * 新车购置价含税
     */
    private BigDecimal taxPrice;

    /**
     * 品牌型号
     */
    private String vehicleCode;

    /**
     * 车型品牌全称
     */
    private String vehicleName;

    /**
     * 年款
     */
    private String yearstyle;

    /**
     * 手动挡/自动挡
     */
    private String gearbox;

    /**
     * 排气量
     */
    private Double displacement;

    /**
     * 整备质量
     */
    private Integer fullWeight;

    /**
     * 车型信息
     * @return car_modelinfos_id 车型信息
     */
    public Integer getCarModelinfosId() {
        return carModelinfosId;
    }

    /**
     * 车型信息
     * @param carModelinfosId 车型信息
     */
    public void setCarModelinfosId(Integer carModelinfosId) {
        this.carModelinfosId = carModelinfosId;
    }

    /**
     * 车型ID
     * @return vehicle_id 车型ID
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 车型ID
     * @param vehicleId 车型ID
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 上市年月
     * @return maket_date 上市年月
     */
    public String getMaketDate() {
        return maketDate;
    }

    /**
     * 上市年月
     * @param maketDate 上市年月
     */
    public void setMaketDate(String maketDate) {
        this.maketDate = maketDate == null ? null : maketDate.trim();
    }

    /**
     * 新车购置价
     * @return price 新车购置价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 新车购置价
     * @param price 新车购置价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 座位数
     * @return seat 座位数
     */
    public Integer getSeat() {
        return seat;
    }

    /**
     * 座位数
     * @param seat 座位数
     */
    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    /**
     * 新车购置价含税
     * @return tax_price 新车购置价含税
     */
    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    /**
     * 新车购置价含税
     * @param taxPrice 新车购置价含税
     */
    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    /**
     * 品牌型号
     * @return vehicle_code 品牌型号
     */
    public String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * 品牌型号
     * @param vehicleCode 品牌型号
     */
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode == null ? null : vehicleCode.trim();
    }

    /**
     * 车型品牌全称
     * @return vehicle_name 车型品牌全称
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * 车型品牌全称
     * @param vehicleName 车型品牌全称
     */
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName == null ? null : vehicleName.trim();
    }

    /**
     * 年款
     * @return yearStyle 年款
     */
    public String getYearstyle() {
        return yearstyle;
    }

    /**
     * 年款
     * @param yearstyle 年款
     */
    public void setYearstyle(String yearstyle) {
        this.yearstyle = yearstyle == null ? null : yearstyle.trim();
    }

    /**
     * 手动挡/自动挡
     * @return gearbox 手动挡/自动挡
     */
    public String getGearbox() {
        return gearbox;
    }

    /**
     * 手动挡/自动挡
     * @param gearbox 手动挡/自动挡
     */
    public void setGearbox(String gearbox) {
        this.gearbox = gearbox == null ? null : gearbox.trim();
    }

    /**
     * 排气量
     * @return displacement 排气量
     */
    public Double getDisplacement() {
        return displacement;
    }

    /**
     * 排气量
     * @param displacement 排气量
     */
    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    /**
     * 整备质量
     * @return full_weight 整备质量
     */
    public Integer getFullWeight() {
        return fullWeight;
    }

    /**
     * 整备质量
     * @param fullWeight 整备质量
     */
    public void setFullWeight(Integer fullWeight) {
        this.fullWeight = fullWeight;
    }
}