package com.cjkj.insurance.entity;

public class Invoiceinfo {
    /**
     * 发票信息
     */
    private Integer invoiceinfoId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * car_info-ID
     */
    private Integer carInfoId;

    /**
     * 发票类型,0-增值税普通发票,1-增值税专用发票
     */
    private String invoiceType;

    /**
     * 开户银行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String accountNumber;

    /**
     * 纳税人识别号/统一社会信用代码
     */
    private String identifyNumber;

    /**
     * 纳税登记电话
     */
    private String registerPhone;

    /**
     * 纳税登记地址
     */
    private String registerAddress;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 供应商ID
     */
    private String prvId;

    /**
     * 发票信息
     * @return invoiceInfo_id 发票信息
     */
    public Integer getInvoiceinfoId() {
        return invoiceinfoId;
    }

    /**
     * 发票信息
     * @param invoiceinfoId 发票信息
     */
    public void setInvoiceinfoId(Integer invoiceinfoId) {
        this.invoiceinfoId = invoiceinfoId;
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
     * 发票类型,0-增值税普通发票,1-增值税专用发票
     * @return invoice_type 发票类型,0-增值税普通发票,1-增值税专用发票
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * 发票类型,0-增值税普通发票,1-增值税专用发票
     * @param invoiceType 发票类型,0-增值税普通发票,1-增值税专用发票
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    /**
     * 开户银行名称
     * @return bank_name 开户银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 开户银行名称
     * @param bankName 开户银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 银行账号
     * @return account_number 银行账号
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 银行账号
     * @param accountNumber 银行账号
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    /**
     * 纳税人识别号/统一社会信用代码
     * @return identify_number 纳税人识别号/统一社会信用代码
     */
    public String getIdentifyNumber() {
        return identifyNumber;
    }

    /**
     * 纳税人识别号/统一社会信用代码
     * @param identifyNumber 纳税人识别号/统一社会信用代码
     */
    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber == null ? null : identifyNumber.trim();
    }

    /**
     * 纳税登记电话
     * @return register_phone 纳税登记电话
     */
    public String getRegisterPhone() {
        return registerPhone;
    }

    /**
     * 纳税登记电话
     * @param registerPhone 纳税登记电话
     */
    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone == null ? null : registerPhone.trim();
    }

    /**
     * 纳税登记地址
     * @return register_address 纳税登记地址
     */
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**
     * 纳税登记地址
     * @param registerAddress 纳税登记地址
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress == null ? null : registerAddress.trim();
    }

    /**
     * 电子邮箱
     * @return email 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
}