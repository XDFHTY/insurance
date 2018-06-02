package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.InsureInfo;
import com.cjkj.insurance.entity.Invoiceinfo;

import java.util.List;
import java.util.Map;

public interface InvoiceinfoMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer invoiceinfoId);

    /**
     *
     * @mbggenerated
     */
    int insert(Invoiceinfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Invoiceinfo record);

    /**
     *
     * @mbggenerated
     */
    Invoiceinfo selectByPrimaryKey(Integer invoiceinfoId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Invoiceinfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Invoiceinfo record);


    //检查发票信息是否已存在
    public Invoiceinfo findInvoiceinfo(Invoiceinfo invoiceinfo);


}