package com.cjkj.insurance.controller;

import com.cjkj.insurance.entity.other.*;
import com.cjkj.insurance.service.InsuranceService;
import com.cjkj.insurance.service.MsgHandleService;
import com.cjkj.insurance.utils.ApiCode;
import com.cjkj.insurance.utils.ApiResult;
import com.cjkj.insurance.utils.TimeToString;
import com.cjkj.insurance.utils.json.JSONUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/insurance")
@Api(tags = "车保管理业务")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService ;

    @Autowired
    private MsgHandleService msgHandleService;





    /**
     * 3.获取token
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getToken")
    @ApiOperation("3添加接口访问规则中所需的header信息")
    
    @ApiImplicitParam(name = "id",value = "用户ID",required = true)
    public ApiResult getToken( int id,
                               HttpServletRequest request) {
        request.getSession().setAttribute("userId",id);

        ApiResult a = new ApiResult();
        a = insuranceService.getToken(request,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;
    }

    /*
     **4.获取投保地区
     * 440000
     */
    @PostMapping("/getAgreementAreas")
    @ApiOperation("4获取投保地区")
    
    @ApiImplicitParam(name = "agreementProvCode",value = "省编码或null",required = true)
    public ApiResult getAgreementAreas(HttpServletRequest request,
                                       String agreementProvCode) {
        ApiResult a =new ApiResult();
        a=insuranceService.getAgreementAreas(request,agreementProvCode,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;
    }

    /**
     * 4+.获取地区车牌
     */
    @ApiOperation("4+.获取地区车牌")
    @GetMapping("/getLicensePlateByCityId")
    @ApiImplicitParam(name = "cityId",value = "城市编号",required = true)
    public ApiResult getLicensePlateByCityId(String cityId){
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(insuranceService.findLicensePlateByCityId(cityId));
        return apiResult;

    }

    /*5.获取供应商列表
     * 例如，东莞市 : 441900
     **/
    @PostMapping("/getProviders")
    
    @ApiOperation("5获取供应商列表")
    @ApiImplicitParam(name = "insureAreaCode",value = "国标地区编码(市级)",required = true)
    public ApiResult getProviders(HttpServletRequest request,
                                  String insureAreaCode ) {
        ApiResult a =new ApiResult();

        a = insuranceService.getProviders(request,insureAreaCode,a);
        System.out.println(a.toString());
        System.out.println(TimeToString.DateToStr(new Date())+a);
        return a;
    }

    /*  6	创建报价任务
    {"insureAreaCode":"441900","carLicenseNo":"粤STTT33","name":"汪莹"}
        **/
    @PostMapping("/createTaskA")
    
    @ApiOperation("6创建报价任务A")
    public ApiResult createTaskA(HttpServletRequest request,
                                 @ApiParam(name = "params",value = "insureAreaCode:地区编码(市级), carInfo(carLicenseNo:车牌号)，carOwner(name：车主姓名)",required = true)
                                 @RequestBody ReqCreateTaskA reqCreateTaskA) {
        ApiResult a =new ApiResult();
        a = insuranceService.createTaskA(reqCreateTaskA,request,a);
        if(-1 == a.getCode()){
            a.setParams("车辆信息查询失败，请跳转至创建报价（标准接口），即/CreateTaskB所对应的界面进行创建报价");
        }
        System.out.println(TimeToString.DateToStr(new Date())+":"+a);
        return a;
    }

    /*
     *7	创建报价任务（标准接口）
     **/
    @PostMapping("/createTaskB")
    
    @ApiOperation("7创建报价任务B（标准接口）")
    public ApiResult createTaskB(HttpServletRequest request,
                                 @ApiParam(name = "params",value = "很多信息",required = true)
                                 @RequestBody ReqCreateTaskB reqCreateTaskB) {
        ApiResult a =new ApiResult();
        a = insuranceService.createTaskB(reqCreateTaskB,request,a);
        System.out.println(TimeToString.DateToStr(new Date())+a);
        return a;
    }

    /*
     *8查询车型信息
     **/
    @PostMapping("/queryCarModelInfos")
    
    @ApiOperation("8查询车型信息")
    public ApiResult queryCarModelInfos(HttpServletRequest request,
                                        @ApiParam(name = "params",value = "pageSize:每页记录数(限制50),pageNum:第几页，vehicleName:车型名称/VIN码,carLicenseNo:车牌号,registDate:初登日期,",required = true)
                                        @RequestBody JSONObject params) {
        ApiResult a =new ApiResult();
        a=insuranceService.queryCarModelInfos(request,params,a);
        System.out.println(TimeToString.DateToStr(new Date())+a);
        return a;

    }

    /*  9修改报价/投保数据
     **/
    @PostMapping("/updateTask")
    
    @ApiOperation("9修改报价/投保数据")

    public ApiResult updateTask(HttpServletRequest request,
                                @ApiParam(name = "params",value = "提交需要修改的数据，JSON格式",required = true)
                                @RequestBody ReqUpdateTask reqUpdateTask) {
        ApiResult a =new ApiResult();
        a=insuranceService.updateTask(request,reqUpdateTask,a);


        System.out.println(TimeToString.DateToStr(new Date())+a);
        return a;

    }

    /*  10提交报价,等待报价信息结果    taskId:2629432
     **/
    @PostMapping("/submitQuote")
    
    @ApiOperation("10提交报价,等待报价信息结果")

    public ApiResult submitQuote(HttpServletRequest request,
                                 @ApiParam(name = "params",value = "提交报价任务==" +
                                         "初次提交:{taskId:任务号},重新提交=={taskId: 任务号,prvId: 供应商id}",required = true)
                                 @RequestBody JSONObject params) {
        ApiResult a =new ApiResult();
        System.out.println("传给提交报价任务的参数为："+params.toString());
        a=insuranceService.submitQuote(request,params,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;

    }

    /*  11	提交核保任务
     **/
    @PostMapping("/submitInsure")
    
    @ApiOperation("11提交核保任务")
    public ApiResult submitInsure(
            HttpServletRequest request,
            @ApiParam(name = "params",value = "提交核保任务（需提供taskId与供应商Id）",required = true)
            @RequestBody JSONObject params) {
        ApiResult a =new ApiResult();
        a=insuranceService.submitInsure(params,request,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;

    }
    /*
     * 12回调接口
     **/
    @PostMapping("/finalState")
    
    @ApiOperation("12回调接口")
    public void finalState(@RequestBody String jsonStr) {
        System.out.println("================================泛华回调=================================================");
        System.out.println("jsonStr===>"+jsonStr);
        System.out.println("================================泛华回调=================================================");

        //回调信息处理
        insuranceService.finalState(jsonStr);



    }

    /*  13	影像识别
     **/
    @PostMapping("/recognizeImage")
    
    @ApiOperation("13影像识别")
    public ApiResult recognizeImage(
            @ApiParam(name = "params",value = "imageType: 影像类型," +
                    "imageMode: 图片格式(jpg、png、bmp)," +
                    "imageUrl: 影像URL," +
                    "imageContent: 影像内容(BASE64编码)",required = true)
            @RequestBody JSONObject params, HttpServletRequest request) {
        ApiResult a =new ApiResult();
        a = insuranceService.recognizeImage(params,request,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;

    }


    /*
     14影像上传
     **/
    @PostMapping("/uploadImage")
    
    @ApiOperation("14影像上传")
    public ApiResult uploadImage(HttpServletRequest request,
                                 @ApiParam(name = "params",value = "taskId=任务号，imageInfos=影像信息集合" +
                                         "【imageMode: 图片格式(jpg、png、bmp),imageUrl: 影像URL,imageContent: 影像内容(BASE64编码)】",required = true)
                                 @RequestBody ReqImgUpload reqImgUpload) {
        ApiResult a =new ApiResult();
        a = insuranceService.uploadImage(reqImgUpload,request,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;

    }

    /*
     15支付
      **/
    @PostMapping("/pay")
    
    @ApiOperation("15支付")

    public ApiResult pay(HttpServletRequest request,
                         @ApiParam(name = "params",value = "提交核保任务（需提供taskId与供应商Id）",required = true)
                         @RequestBody JSONObject params) {
        ApiResult a =new ApiResult();
        a=insuranceService.pay(params,request,a);
        System.out.println(TimeToString.DateToStr(new Date())+a.toString());
        return a;

    }

    /**
     * 查询所有的报价结果
     *
     * @return
     */
    @ApiOperation("20---查询所有的回调结果")
    
    @PostMapping("/findAllCallback")
    public ApiResult findAllCallback(HttpServletRequest request,
                                     @ApiParam(name = "map",value = "taskId=任务号,taskStates=任务状态码集合【{\"taskId\":\"2637743\",\"taskStates\":[13,14]}】",required = true)
                                     @RequestBody Map map){
        HttpSession session = request.getSession();

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData( msgHandleService.findAllCallback(session,map));
        return apiResult;
    }

}
