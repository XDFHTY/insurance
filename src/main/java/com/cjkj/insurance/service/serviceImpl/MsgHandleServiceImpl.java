package com.cjkj.insurance.service.serviceImpl;

import com.cjkj.insurance.entity.*;
import com.cjkj.insurance.entity.other.*;
import com.cjkj.insurance.mapper.*;
import com.cjkj.insurance.service.MsgHandleService;
import com.cjkj.insurance.utils.TimeToString;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 处理用户信息
 */
@Service
@Transactional
public class MsgHandleServiceImpl implements MsgHandleService {

    @Resource
    private CarInfoMapper carInfoMapper;

    @Resource
    private CarOwnerMapper carOwnerMapper;

    @Resource
    private RespMsgMapper respMsgMapper;

    @Resource
    private InsureInfoMapper insureInfoMapper;

    @Resource
    private RiskKindsMapper riskKindsMapper;

    @Resource
    private UserImgMapper userImgMapper;

    @Resource
    private DeliveryMapper deliveryMapper;

    @Resource
    private InsureSupplysMapper insureSupplysMapper;

    @Resource
    private InvoiceinfoMapper invoiceinfoMapper;

    @Resource
    private ScoreRateMapper scoreRateMapper;

    @Resource
    private CallbackMapper callbackMapper;
    /**
     * 创建报价任务B接口用户提交的数据
     * @param session
     * @param reqCreateTaskB
     * @return
     */
    @Override
    public boolean addUserCarInfo(HttpSession session, ReqCreateTaskB reqCreateTaskB){
        int userId = (Integer)session.getAttribute("userId");
        String taskId = (String) session.getAttribute("taskId");
        //车辆信息
        CarInfo carInfo = reqCreateTaskB.getCarInfo();
        carInfo.setUserId(userId);
        carInfo.setTaskId(taskId);
        carInfo.setCreateTime(new Date());
        carInfoMapper.insertSelective(carInfo);

        session.setAttribute("carInfoId",carInfo.getCarInfoId());

        //供应商ID
        List<Providers> providers = reqCreateTaskB.getProviders();
        int i = 0;

        //循环添加每个供应商的报价用户信息
        for (Providers providers1 : providers){



            //车主信息
            CarOwner carOwner = reqCreateTaskB.getCarOwner();
            carOwner.setMsgType("1");
            carOwner.setUserId(userId);
            carOwner.setCarInfoId(carInfo.getCarInfoId());
            carOwner.setTaskId(taskId);
            carOwner.setPrvId(providers1.getPrvId());
            i += carOwnerMapper.insertSelective(carOwner);

            //投保人信息
            CarOwner applicant = reqCreateTaskB.getApplicant();
            if(applicant == null){
                applicant = carOwner;
            }
            applicant.setMsgType("2");
            applicant.setUserId(userId);
            applicant.setCarInfoId(carInfo.getCarInfoId());
            applicant.setTaskId(taskId);
            applicant.setPrvId(providers1.getPrvId());
            i += carOwnerMapper.insertSelective(applicant);

            //被保人信息
            CarOwner insured =  reqCreateTaskB.getInsured();
            if(insured == null){
                insured = carOwner;
            }
            insured.setMsgType("3");
            insured.setUserId(userId);
            insured.setCarInfoId(carInfo.getCarInfoId());
            insured.setTaskId(taskId);
            insured.setPrvId(providers1.getPrvId());
            i += carOwnerMapper.insertSelective(insured);

            //索赔权益人信息
            CarOwner beneficiary = reqCreateTaskB.getBeneficiary();
            if(beneficiary == null){
                beneficiary = carOwner;
            }
            beneficiary.setMsgType("4");
            beneficiary.setUserId(userId);
            beneficiary.setCarInfoId(carInfo.getCarInfoId());
            beneficiary.setTaskId(taskId);  //任务号
            beneficiary.setPrvId(providers1.getPrvId());

            i += carOwnerMapper.insertSelective(beneficiary);

        }


        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateUserMsg(HttpSession session, ReqUpdateTask reqUpdateTask) {
        int userId = (Integer)session.getAttribute("userId");
        //供应商ID
        List<Providers> providersList = reqUpdateTask.getProviders();




        //车辆信息
        CarInfo carInfo = reqUpdateTask.getCarInfo();
        if(carInfo != null){
            carInfo.setUserId(userId);
            carInfo.setTaskId(reqUpdateTask.getTaskId());  //任务号
            //检查 用户-任务号 是否已存在
            List<CarInfo> carInfos = carInfoMapper.findCarInfos(carInfo);
            if(carInfos.size()>0){ //数据已存在
                carInfo.setCarInfoId(carInfos.get(0).getCarInfoId());
                //修改信息
                carInfoMapper.updateByPrimaryKeySelective(carInfo);

            }else {

                carInfo.setUpdateTime(new Date());
                carInfoMapper.insertSelective(carInfo);
            }
        }


        int i = 0;
        //循环添加信息
        for (Providers providers1 : providersList){



            //车主信息
            CarOwner carOwner = reqUpdateTask.getCarOwner();
            carOwner.setMsgType("1");
            carOwner.setUserId(userId);
            carOwner.setCarInfoId(carInfo.getCarInfoId());
            carOwner.setTaskId(reqUpdateTask.getTaskId());  //任务号
            carOwner.setPrvId(providers1.getPrvId());  //供应商Id

            //检查 用户ID-carInfoId-任务号 车主信息是否存在
            CarOwner oldCarOwner = carOwnerMapper.findOldCarOwner(carOwner);
            if(oldCarOwner == null){
                System.out.println("====================车主信息为null========================");
                i += carOwnerMapper.insertSelective(carOwner);

            }else {
                carOwner.setCarOwnerId(oldCarOwner.getCarOwnerId());
                i += carOwnerMapper.updateByPrimaryKeySelective(carOwner);
            }

            //投保人信息
            CarOwner applicant = reqUpdateTask.getApplicant();
            if(applicant == null){

                applicant = reqUpdateTask.getCarOwner();
            }
            applicant.setMsgType("2");
            applicant.setUserId(userId);
            applicant.setCarInfoId(carInfo.getCarInfoId());
            applicant.setTaskId(reqUpdateTask.getTaskId());  //任务号
            applicant.setPrvId(providers1.getPrvId());  //供应商Id
            //检查 用户ID-carInfoId-身份证-任务号 车主信息是否存在
            CarOwner oldApplicant = carOwnerMapper.findOldCarOwner(applicant);
            if(oldApplicant == null){
                System.out.println("====================投保人信息为null========================");
                i += carOwnerMapper.insertSelective(applicant);

            }else {

                applicant.setCarOwnerId(oldApplicant.getCarOwnerId());
                i += carOwnerMapper.updateByPrimaryKeySelective(applicant);
            }

            //被保人信息
            CarOwner insured =  reqUpdateTask.getInsured();
            if(insured == null){
                insured = reqUpdateTask.getCarOwner();
            }
            insured.setMsgType("3");
            insured.setUserId(userId);
            insured.setCarInfoId(carInfo.getCarInfoId());
            insured.setTaskId(reqUpdateTask.getTaskId());  //任务号
            insured.setPrvId(providers1.getPrvId());  //供应商Id
            //检查 用户ID-carInfoId-身份证-任务号 车主信息是否存在
            CarOwner oldInsured = carOwnerMapper.findOldCarOwner(insured);
            if(oldInsured == null){
                System.out.println("====================被保人信息为null========================");
                i += carOwnerMapper.insertSelective(insured);

            }else {

                insured.setCarOwnerId(oldInsured.getCarOwnerId());
                i += carOwnerMapper.updateByPrimaryKeySelective(insured);
            }


            //索赔权益人信息
            CarOwner beneficiary = reqUpdateTask.getBeneficiary();
            if(beneficiary == null){
                beneficiary = reqUpdateTask.getCarOwner();
            }
            beneficiary.setMsgType("4");
            beneficiary.setUserId(userId);
            beneficiary.setCarInfoId(carInfo.getCarInfoId());
            beneficiary.setTaskId(reqUpdateTask.getTaskId());  //任务号
            beneficiary.setPrvId(providers1.getPrvId());  //供应商Id
            //检查 用户ID-carInfoId-身份证-任务号 车主信息是否存在
            CarOwner oldBeneficiary = carOwnerMapper.findOldCarOwner(beneficiary);
            if(oldBeneficiary == null){
                System.out.println("====================索赔人信息为null========================");
                i += carOwnerMapper.insertSelective(beneficiary);
            }else {

                beneficiary.setCarOwnerId(oldBeneficiary.getCarOwnerId());
                i += carOwnerMapper.updateByPrimaryKeySelective(beneficiary);
            }


            //配送信息
            Delivery delivery = reqUpdateTask.getDelivery();
            if(delivery != null){

                delivery.setUserId(userId);
                delivery.setCarInfoId(carInfo.getCarInfoId());
                delivery.setTaskId(reqUpdateTask.getTaskId());  //任务号
                delivery.setPrvId(providers1.getPrvId());  //供应商Id

                //检查配送信息是否存在
                Delivery oldDelivery = deliveryMapper.findDelivery(delivery);
                if(oldDelivery == null){
                    i += deliveryMapper.insertSelective(delivery);

                }else {
                    delivery.setDeliveryId(oldDelivery.getDeliveryId());
                    i += deliveryMapper.updateByPrimaryKeySelective(delivery);
                }
            }


            //发票信息
            Invoiceinfo invoiceinfo = reqUpdateTask.getInvoiceInfo();
            if(invoiceinfo != null){
                invoiceinfo.setUserId(userId);
                invoiceinfo.setCarInfoId(carInfo.getCarInfoId());
                invoiceinfo.setTaskId(reqUpdateTask.getTaskId());  //任务号
                invoiceinfo.setPrvId(providers1.getPrvId());  //供应商Id

                System.out.println();

                //检查发票信息是否已存在
                Invoiceinfo oldInvoiceinfo = invoiceinfoMapper.findInvoiceinfo(invoiceinfo);
                if(oldInvoiceinfo == null){
                    i += invoiceinfoMapper.insertSelective(invoiceinfo);

                }else {

                    invoiceinfo.setInvoiceinfoId(oldInvoiceinfo.getInvoiceinfoId());

                    i += invoiceinfoMapper.updateByPrimaryKeySelective(invoiceinfo);
                }
            }

        }

        if( i > 0){
            return true;
        }else {

            return false;
        }


    }

    //根据 userId 和 carInfoId 更新 任务号taskId
    @Override
    public boolean updateCarInfo(HttpSession session, String taskId) {
        int userId = (Integer) session.getAttribute("userId");
        int carInfoId = (Integer) session.getAttribute("carInfoId");
        CarInfo carInfo = new CarInfo();
        carInfo.setUserId(userId);
        carInfo.setCarInfoId(carInfoId);
        carInfo.setTaskId(taskId);
        int i = carInfoMapper.updateCarinfo(carInfo);
        if(i > 0){
            return true;
        }else {

            return false;
        }

    }

    @Override
    public boolean updateCarOwner(HttpSession session, String taskId) {
        int userId = (Integer) session.getAttribute("userId");
        int carInfoId = (Integer) session.getAttribute("carInfoId");
        CarOwner carOwner = new CarOwner();
        carOwner.setUserId(userId);
        carOwner.setCarInfoId(carInfoId);
        carOwner.setTaskId(taskId);
        int i = carOwnerMapper.updateCarOwner(carOwner);

        if(i > 0){
            return true;
        }else {

            return false;
        }
    }

    @Override
    public boolean addFinalState( RespFinalState respFinalState) {
        String taskId = respFinalState.getTaskId();
        String prvId = respFinalState.getPrvId();
        String taskState = respFinalState.getTaskState();


        /**
         * 检查 此任务号 、 供应商ID 、信息状态 信息 是否已存在信息，如果存在，则更新回调信息
         */
        Map map = new HashMap();
        map.put("taskId",taskId);
        map.put("prvId",prvId);
        map.put("taskState",taskState);

        RespMsg respMsg0  = respMsgMapper.findRespMsgByTaskId(map);
        RespMsg respMsg = new RespMsg();

        if(respMsg0 != null){  //存在
            //回调信息已存在
            //删除旧信息
            respMsgMapper.deleteByPrimaryKey(respMsg0.getRespMsgId());
        }


        //处理返回的基础信息
        respMsg.setTaskId(taskId);
        respMsg.setPrvId(prvId);
        respMsg.setPrvName(respFinalState.getPrvName());
        respMsg.setRespCode(respFinalState.getRespCode());
        respMsg.setErrorMsg(respFinalState.getErrorMsg());
        respMsg.setChannelId(respFinalState.getChannelId());
        respMsg.setChannelUserId(respFinalState.getChannelUserId());
        respMsg.setTaskState(respFinalState.getTaskState());
        respMsg.setTaskStateDescription(respFinalState.getTaskStateDescription());
        respMsg.setMsgType(respFinalState.getMsgType());
        respMsg.setInspectionCode(respFinalState.getInspectionCode());
        respMsg.setCreateTime(new Date());
        respMsg.setRespJson(respFinalState.getRespJson());

        if(respFinalState.getQuoteValidTime() != null && respFinalState.getQuoteValidTime() !=""){
            respMsg.setQuoteValidTime(TimeToString.StrToDate(respFinalState.getQuoteValidTime()));

        }
        if(respFinalState.getPayValidTime() != null && respFinalState.getPayValidTime() != ""){
            respMsg.setPayValidTime(TimeToString.StrToDate(respFinalState.getPayValidTime()));
        }
        //保存返回的基础信息
        respMsgMapper.insertSelective(respMsg);


//            //根据 respMsgId 更新报价回调信息内容和状态
//            respMsg.setRespMsgId(respMsg0.getRespMsgId());
//            respMsg.setTaskId(respMsg0.getTaskId());
//            respMsg.setPrvId(respMsg0.getPrvId());
//            respMsg.setTaskState(respMsg0.getTaskState());
//
//            respMsg.setPrvName(respFinalState.getPrvName());
//            respMsg.setRespCode(respFinalState.getRespCode());
//            respMsg.setErrorMsg(respFinalState.getErrorMsg());
//            respMsg.setChannelId(respFinalState.getChannelId());
//            respMsg.setChannelUserId(respFinalState.getChannelUserId());
//            respMsg.setTaskState(respFinalState.getTaskState());
//            respMsg.setTaskStateDescription(respFinalState.getTaskStateDescription());
//            respMsg.setMsgType(respFinalState.getMsgType());
//            respMsg.setInspectionCode(respFinalState.getInspectionCode());
//            respMsg.setCreateTime(new Date());
//            respMsg.setRespJson(respFinalState.getRespJson());
//            if(respFinalState.getQuoteValidTime() != null && respFinalState.getQuoteValidTime() !=""){
//                respMsg.setQuoteValidTime(TimeToString.StrToDate(respFinalState.getQuoteValidTime()));
//
//            }
//            if(respFinalState.getPayValidTime() != null && respFinalState.getPayValidTime() != ""){
//                respMsg.setPayValidTime(TimeToString.StrToDate(respFinalState.getPayValidTime()));
//            }
//            //更新 回调信息
//            respMsgMapper.updateByPrimaryKeySelective(respMsg);




        //根据 任务号检查 carInfo是否存在
        CarInfo carInfo = carInfoMapper.findCarInfo(taskId);
        if(carInfo == null){  //基础信息不存在
            carInfo = respFinalState.getCarInfo();
            carInfoMapper.insertSelective(carInfo);
        }

        //根据 任务号 和 carInfoId 检查 carOwner是否存在
        Map respCarOwnerMap = new HashMap();
        respCarOwnerMap.put("taskId",taskId);
        respCarOwnerMap.put("carInfoId",carInfo.getCarInfoId());
        respCarOwnerMap.put("prvId",prvId); //供应商ID
        respCarOwnerMap.put("msgType","1");

        CarOwner carOwner = carOwnerMapper.findCarOwner(respCarOwnerMap);
        if(carOwner == null){  //车主信息不存在
            carOwner = respFinalState.getCarOwner();
            carOwner.setCarInfoId(carInfo.getCarInfoId());
            carOwner.setMsgType("1");
            carOwnerMapper.insertSelective(carOwner);
        }

        /**
         * 根据carInfoID 、respMsgID、任务号、供应商ID 查询保险配置信息是否存在
         * 如果存在，则修改
         */
        Map insureInfoMap = new HashMap();
        insureInfoMap.put("carInfoId",carInfo.getCarInfoId());
        insureInfoMap.put("respMsgId",respMsg.getRespMsgId());
        insureInfoMap.put("prvId",prvId);
        insureInfoMap.put("taskId",taskId);

        List<InsureInfo> insureInfoList = insureInfoMapper.findAllInsureInfoByMap(insureInfoMap);

        if(insureInfoList.size() == 0){  //信息不存在

            //处理保险配置信息
            EfcInsureInfo efcInsureInfo1 = respFinalState.getInsureInfo().getEfcInsureInfo();
            TaxInsureInfo taxInsureInfo1 = respFinalState.getInsureInfo().getTaxInsureInfo();
            BizInsureInfo bizInsureInfo1 = respFinalState.getInsureInfo().getBizInsureInfo();

            InsureInfo efcInsureInfo = new InsureInfo();
            InsureInfo taxInsureInfo = new InsureInfo();
            InsureInfo bizInsureInfo = new InsureInfo();

            efcInsureInfo.setMsgType("1");  //交强险
            efcInsureInfo.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
            efcInsureInfo.setTaskId(taskId);  //任务号
            efcInsureInfo.setPrvId(prvId);  //供应商ID
            efcInsureInfo.setRespMsgId(respMsg.getRespMsgId());  //响应信息ID
            efcInsureInfo.setTotalPremium(respFinalState.getInsureInfo().getTotalPremium());  //总保费
            if(efcInsureInfo1 != null){  //存在
                efcInsureInfo.setStartDate(efcInsureInfo1.getStartDate());
                efcInsureInfo.setEndDate(efcInsureInfo1.getEndDate());
                efcInsureInfo.setAmount(efcInsureInfo1.getAmount());
                efcInsureInfo.setPremium(efcInsureInfo1.getPremium());
                efcInsureInfo.setPolicyNo(efcInsureInfo1.getPolicyNo());
                efcInsureInfo.setDiscountRate(efcInsureInfo1.getDiscountRate());

            }

            taxInsureInfo.setMsgType("2");  //车船税
            taxInsureInfo.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
            taxInsureInfo.setTaskId(taskId);  //任务号
            taxInsureInfo.setPrvId(prvId);  //供应商ID
            taxInsureInfo.setRespMsgId(respMsg.getRespMsgId());  //响应信息ID
            taxInsureInfo.setTotalPremium(respFinalState.getInsureInfo().getTotalPremium());  //总保费
            if(taxInsureInfo1 != null){
                taxInsureInfo.setIsPaymentTax(taxInsureInfo1.getIsPaymentTax());
                taxInsureInfo.setTaxFee(taxInsureInfo1.getTaxFee());
                taxInsureInfo.setLateFee(taxInsureInfo1.getLateFee());

            }

            bizInsureInfo.setMsgType("3");  //商业险
            bizInsureInfo.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
            bizInsureInfo.setTaskId(taskId);  //任务号
            bizInsureInfo.setPrvId(prvId);  //供应商ID
            bizInsureInfo.setRespMsgId(respMsg.getRespMsgId());  //响应信息ID
            bizInsureInfo.setTotalPremium(respFinalState.getInsureInfo().getTotalPremium());  //总保费
            if(bizInsureInfo1 != null){
                bizInsureInfo.setStartDate(bizInsureInfo1.getStartDate());
                bizInsureInfo.setEndDate(bizInsureInfo1.getEndDate());
                bizInsureInfo.setPremium(bizInsureInfo1.getPremium());
                bizInsureInfo.setDiscountRate(bizInsureInfo1.getDiscountRate());
                bizInsureInfo.setPolicyNo(bizInsureInfo1.getPolicyNo());
                bizInsureInfo.setNfcPremium(bizInsureInfo1.getNfcPremium());
            }

            insureInfoMapper.insertSelective(efcInsureInfo);
            insureInfoMapper.insertSelective(taxInsureInfo);
            insureInfoMapper.insertSelective(bizInsureInfo);
        }else {

            //处理保险配置信息
            EfcInsureInfo efcInsureInfo1 = respFinalState.getInsureInfo().getEfcInsureInfo();
            TaxInsureInfo taxInsureInfo1 = respFinalState.getInsureInfo().getTaxInsureInfo();
            BizInsureInfo bizInsureInfo1 = respFinalState.getInsureInfo().getBizInsureInfo();

            InsureInfo efcInsureInfo = new InsureInfo();
            InsureInfo taxInsureInfo = new InsureInfo();
            InsureInfo bizInsureInfo = new InsureInfo();

            for(InsureInfo insureInfo : insureInfoList){
                if(insureInfo.getMsgType().equals("1")){
                    efcInsureInfo = insureInfo;
                    efcInsureInfo.setInsureInfoId(insureInfo.getInsureInfoId());
                }else if(insureInfo.getMsgType().equals("2")){
                    taxInsureInfo = insureInfo;
                    taxInsureInfo.setInsureInfoId(insureInfo.getInsureInfoId());

                }else if(insureInfo.getMsgType().equals("3")){
                    bizInsureInfo = insureInfo;
                    bizInsureInfo.setInsureInfoId(insureInfo.getInsureInfoId());

                }
            }



            efcInsureInfo.setTotalPremium(respFinalState.getInsureInfo().getTotalPremium());  //总保费
            if(efcInsureInfo1 != null){
                efcInsureInfo.setStartDate(efcInsureInfo1.getStartDate());
                efcInsureInfo.setEndDate(efcInsureInfo1.getEndDate());
                efcInsureInfo.setAmount(efcInsureInfo1.getAmount());
                efcInsureInfo.setPremium(efcInsureInfo1.getPremium());
                efcInsureInfo.setPolicyNo(efcInsureInfo1.getPolicyNo());
                efcInsureInfo.setDiscountRate(efcInsureInfo1.getDiscountRate());

            }

            taxInsureInfo.setTotalPremium(respFinalState.getInsureInfo().getTotalPremium());  //总保费
            if(taxInsureInfo1 != null){
                taxInsureInfo.setIsPaymentTax(taxInsureInfo1.getIsPaymentTax());
                taxInsureInfo.setTaxFee(taxInsureInfo1.getTaxFee());
                taxInsureInfo.setLateFee(taxInsureInfo1.getLateFee());
            }

            bizInsureInfo.setTotalPremium(respFinalState.getInsureInfo().getTotalPremium());  //总保费
            if(bizInsureInfo1 != null){

                bizInsureInfo.setStartDate(bizInsureInfo1.getStartDate());
                bizInsureInfo.setEndDate(bizInsureInfo1.getEndDate());
                bizInsureInfo.setPremium(bizInsureInfo1.getPremium());
                bizInsureInfo.setDiscountRate(bizInsureInfo1.getDiscountRate());
                bizInsureInfo.setPolicyNo(bizInsureInfo1.getPolicyNo());
                bizInsureInfo.setNfcPremium(bizInsureInfo1.getNfcPremium());
            }


            insureInfoMapper.updateByPrimaryKeySelective(efcInsureInfo);
            insureInfoMapper.updateByPrimaryKeySelective(taxInsureInfo);
            insureInfoMapper.updateByPrimaryKeySelective(bizInsureInfo);
        }

        /**
         * 根据任务号查询商业险险种信息是否存在
         */
        Map riskKindsMap = new HashMap();
        riskKindsMap.put("taskId",taskId);
        List<RiskKinds> riskKindsList = riskKindsMapper.findAllRiskKindsByTaskId(riskKindsMap);


        if(riskKindsList.size() == 0){
            //商业险险种信息处理
            List<RiskKinds> riskKinds = respFinalState.getInsureInfo().getBizInsureInfo().getRiskKinds();
            for (RiskKinds riskKind : riskKinds){
                riskKind.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                riskKind.setTaskId(taskId);  //任务号
                riskKind.setPrvId(prvId);  //供应商ID
                riskKind.setRespMsgId(respMsg.getRespMsgId());  //响应信息ID
                //保存商业险险种信息
                riskKindsMapper.insertSelective(riskKind);
            }

        }
//        else {
//            //商业险险种信息处理
//            List<RiskKinds> riskKinds = respFinalState.getInsureInfo().getBizInsureInfo().getRiskKinds();
//            for (RiskKinds riskKind : riskKinds){
//                for(RiskKinds riskKinds1:riskKindsList){
//                    if(riskKind.getPrvId().equals(riskKinds1.getPrvId())){  //供应商一致
//
//                        riskKind.setRiskKindsId(riskKinds1.getRiskKindsId());  //主键
//                        riskKind.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
//                        riskKind.setTaskId(taskId);  //任务号
//                        riskKind.setPrvId(prvId);  //供应商ID
//                        riskKind.setRespMsgId(respMsg.getRespMsgId());  //响应信息ID
//
//                    }
//                }
//                //修改商业险险种信息
//                riskKindsMapper.updateByPrimaryKeySelective(riskKind);
//            }
//        }

        //检查是否已存在
        List<UserImg> userImgList = userImgMapper.findAllUserImg(taskId);
        //处理影像提醒信息
        List<ImageInfo> imageInfos = respFinalState.getImageInfos();
        if(userImgList.size() == 0){
            if(imageInfos != null){
                for (ImageInfo imageInfo : imageInfos){
                    UserImg userImg = new UserImg();

                    userImg.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                    userImg.setTaskId(taskId);  //任务号
                    userImg.setPrvId(prvId);  //供应商ID

                    userImg.setImgType(imageInfo.getImageType());
                    userImg.setImgName(imageInfo.getImageName());

                    //添加影像信息数据
                    userImgMapper.insertSelective(userImg);

                }

            }

        }else {

            if(imageInfos != null){
                for (ImageInfo imageInfo : imageInfos){
                    for (UserImg userImg : userImgList){
                        userImg.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                        userImg.setTaskId(taskId);  //任务号
                        userImg.setPrvId(prvId);  //供应商ID

                        userImg.setImgType(imageInfo.getImageType());
                        userImg.setImgName(imageInfo.getImageName());

                        //更新影像信息数据
                        userImgMapper.updateByPrimaryKeySelective(userImg);

                    }


                }

            }

        }





        //检查是否存在
        Delivery delivery1 = deliveryMapper.findDeliveryByTaskId(taskId);
        if(delivery1 == null){
            //处理配送信息
            Delivery delivery = respFinalState.getDelivery();
            if(delivery != null){
                delivery.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                delivery.setTaskId(taskId);  //任务号
                delivery.setPrvId(prvId);  //供应商ID

                //添加配送信息数据
                deliveryMapper.insertSelective(delivery);

            }
        }else {

            //处理配送信息
            Delivery delivery = respFinalState.getDelivery();
            if(delivery != null){
                delivery.setDeliveryId(delivery1.getDeliveryId());  //主键
                delivery.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                delivery.setTaskId(taskId);  //任务号
                delivery.setPrvId(prvId);  //供应商ID

                //添加配送信息数据
                deliveryMapper.updateByPrimaryKeySelective(delivery);

            }

        }


        //查询是否存在
        List<InsureSupplys> insureSupplysList = insureSupplysMapper.findAllInsureSupplysByTaskId(taskId);
        //处理数据项编码数据
        List<InsureSupplys> insureSupplys = respFinalState.getInsureSupplys();

        if(insureInfoList.size() == 0){
            if(insureSupplys != null){
                for(InsureSupplys insureSupplys1 : insureSupplys){
                    insureSupplys1.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                    insureSupplys1.setTaskId(taskId);  //任务号
                    insureSupplys1.setPrvId(prvId);  //供应商ID
                    //添加数据项编码数据
                    insureSupplysMapper.insertSelective(insureSupplys1);

                }

            }
        }else {
            if(insureSupplys != null){
                for (InsureSupplys insureSupplys0 : insureSupplysList){
                    for(InsureSupplys insureSupplys1 : insureSupplys){
                        insureSupplys1.setInsureSupplysId(insureSupplys0.getInsureSupplysId());
                        insureSupplys1.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                        insureSupplys1.setTaskId(taskId);  //任务号
                        insureSupplys1.setPrvId(prvId);  //供应商ID
                        //添加数据项编码数据
                        insureSupplysMapper.updateByPrimaryKeySelective(insureSupplys1);
                    }
                }
            }
        }


        //查询是否存在
        ScoreRate scoreRate0 = scoreRateMapper.findScoreRateByTaskId(taskId);
        if(scoreRate0 == null){
            //处理业务评级数据
            ScoreRate scoreRate = respFinalState.getScoreRate();
            if(scoreRate != null){
                scoreRate.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                scoreRate.setTaskId(taskId);  //任务号
                scoreRate.setPrvId(prvId);  //供应商ID

                //保存业务评级数据
                scoreRateMapper.insertSelective(scoreRate);

            }
        }else {
            //处理业务评级数据
            ScoreRate scoreRate = respFinalState.getScoreRate();
            if(scoreRate != null){
                scoreRate.setScoreRateId(scoreRate0.getScoreRateId());
                scoreRate.setCarInfoId(carInfo.getCarInfoId());  //carInfoId
                scoreRate.setTaskId(taskId);  //任务号
                scoreRate.setPrvId(prvId);  //供应商ID

                //修改业务评级数据
                scoreRateMapper.updateByPrimaryKeySelective(scoreRate);

            }
        }








        System.out.println("=======================数据处理完成=============================");


        return true;
    }

    @Override
    public List<Map> findAllCallback(HttpSession session,Map map) {
        return callbackMapper.findAllCallback(map);
    }
}
