package com.cjkj.insurance.service.serviceImpl;

import com.cjkj.insurance.entity.UserImg;
import com.cjkj.insurance.entity.other.*;
import com.cjkj.insurance.mapper.CarInfoMapper;
import com.cjkj.insurance.mapper.DqLicenceLocationMapper;
import com.cjkj.insurance.mapper.UserImgMapper;
import com.cjkj.insurance.service.InsuranceService;
import com.cjkj.insurance.service.MsgHandleService;
import com.cjkj.insurance.utils.ApiCode;
import com.cjkj.insurance.utils.ApiResult;
import com.cjkj.insurance.utils.RSACoderUtil;
import com.cjkj.insurance.utils.RandomStringUtils;
import com.cjkj.insurance.utils.file.FileUtil;
import com.cjkj.insurance.utils.http.APIHttpClient;
import com.cjkj.insurance.utils.json.JSONUtil;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    @Value("${web.upload-path}")
    String path;
    @Value("${channelUserId}")
    public  String channelUserId;
    @Value("${channelSecret}")
    public  String channelSecret;
    @Value("${privateKey}")
    public  String privateKey;


    @Autowired
    private UserImgMapper userImgMapper;

    @Autowired
    private MsgHandleService msgHandleService;

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Autowired
    private DqLicenceLocationMapper dqLicenceLocationMapper;

    Gson gson = new Gson();


    //获取accessToken
    @Override
    public ApiResult getToken(HttpServletRequest request, ApiResult a) throws Exception {
        JSONObject jsonObject=new JSONObject();
        HttpSession session = request.getSession();
        session.setAttribute("channelId", channelUserId);
        session.setAttribute("channelSecret",channelSecret);
        String response = new APIHttpClient()
                .post("/getToken", request,"~", 1);
        String accessToken="";
        if (response != null ) {
            jsonObject = JSONObject.fromObject(response);
            accessToken = (String) jsonObject.get("accessToken");
        }
        session.setAttribute("accessToken", accessToken);
        String nonceStr = RandomStringUtils.getRandomString2(40);
        session.setAttribute("nonceStr",nonceStr);
        System.out.println("nonceStr===========>"+nonceStr);
        StringBuffer sb =new StringBuffer();
        sb.append(accessToken).append(nonceStr);
        String signStr="";
        try{
            signStr= RSACoderUtil.sign(sb.toString().getBytes(), privateKey);
            session.setAttribute("signStr",signStr);
            System.out.println("signStr===========>"+signStr);

        }catch (Exception e){

        }
        getResult(response,a);
        return a;
    }

    //4获取投保地区
    @Override
    public ApiResult getAgreementAreas(HttpServletRequest request,String agreementProvCode, ApiResult a) throws Exception {
        JSONObject params = new JSONObject();
        params.put("agreementProvCode",agreementProvCode);
//            System.out.println(params.toString());
        String response = new APIHttpClient()
                .post("/getAgreementAreas",request,params.toString(), 2);
//            System.out.println("response: "+response);
        getResult(response,a);
        return a;
    }

    //5获取供应商列表
    @Override
    public ApiResult getProviders(HttpServletRequest request,  String insureAreaCode, ApiResult a) throws Exception {
        JSONObject params = new JSONObject();
        params.put("insureAreaCode",insureAreaCode);
        String response = new APIHttpClient()
                .post("/getProviders", request, params.toString(), 2);
        getResult(response,a);
        return a;
    }

    //6创建报价（接口A）
    @Override
    public ApiResult createTaskA(ReqCreateTaskA reqCreateTaskA, HttpServletRequest request, ApiResult a) throws Exception {

        //设置渠道位置编码
        reqCreateTaskA.setChannelUserId(channelUserId);


        String response = new APIHttpClient()
                .post("/createTaskA", request,JSONUtil.toJSONString(reqCreateTaskA) , 2);

        //将返回的json字符串转换为实体类
        Map map = gson.fromJson(response, Map.class);

        HttpSession session = request.getSession();
        if(map.get("respCode").equals("00")){  //创建报价任务成功
            String taskId = (String) map.get("taskId");
            session.setAttribute("taskId",taskId);  //将报价任务号保存到session
        }
        getResult(response,a);
        a.setData(map);
        return a;
    }

    //7创建报价，接口B
    @Override
    public ApiResult createTaskB(ReqCreateTaskB reqCreateTaskB, HttpServletRequest request, ApiResult a) throws Exception {
        HttpSession session = request.getSession();

        System.out.println("=======================================================");
        System.out.println(reqCreateTaskB.getCarInfo());

        System.out.println("=======================================================");

        //设置渠道位置编码
        reqCreateTaskB.setChannelUserId(channelUserId);
        String response = new APIHttpClient()
                .post("/createTaskB", request, JSONUtil.toJSONString(reqCreateTaskB), 2);
        Map map = gson.fromJson(response,Map.class);



        if(map.get("respCode").equals("00")){  //创建报价任务成功
            String taskId = (String) map.get("taskId");
            session.setAttribute("taskId",taskId);  //将报价任务号保存到session
            //保存用户录入的提交报价任务的数据
            msgHandleService.addUserCarInfo(session,reqCreateTaskB);

            getResult(response,a);
        }else {
            getResult("",a);
        }

        a.setData(map);
        return a;
    }

    //8查询车型信息
    @Override
    public ApiResult queryCarModelInfos(HttpServletRequest request, JSONObject params, ApiResult a) throws Exception {
        String response = new APIHttpClient()
                .post("/queryCarModelInfos", request, params.toString(),2);
        //
        getResult(response,a);
        return a;
    }

    //9更改报价数据
    @Override
    public ApiResult updateTask(HttpServletRequest request, ReqUpdateTask reqUpdateTask, ApiResult a) throws Exception {
        HttpSession session = request.getSession();

        String taskId = (String) session.getAttribute("taskId");
        if(reqUpdateTask.getTaskId() == null){

            reqUpdateTask.setTaskId(taskId);

        }


        ReqUpdateTask reqUpdateTask1 = null;
        try {
            reqUpdateTask1 = (ReqUpdateTask) reqUpdateTask.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(reqUpdateTask1);
        reqUpdateTask1.getCarInfo().setVinCode(null);
        reqUpdateTask1.getCarInfo().setEngineNo(null);
        reqUpdateTask1.getCarInfo().setVehicleName(null);

        /*String json = gson.toJson(reqUpdateTask);
        String response = new APIHttpClient()
                .post("/updateTask", request, json,2);*/

        String json = gson.toJson(reqUpdateTask1);
        String response = new APIHttpClient()
                .post("/updateTask", request, json,2);

        getResult(response,a);


        Map map = gson.fromJson(response, Map.class);


        if(map.get("respCode").equals("00")){  //创建报价任务成功
            session.setAttribute("taskId",taskId);  //将报价任务号保存到session

            //保存用户修改的数据
            msgHandleService.updateUserMsg(session,reqUpdateTask);
            getResult(response,a);
        }else {
            getResult("",a);
        }
        a.setData(map);

        return a;
    }

    //10提交报价,等待报价信息结果
    @Override
    public ApiResult submitQuote(HttpServletRequest request, JSONObject params, ApiResult a) throws Exception {
        String response = new APIHttpClient()
                .post("/submitQuote", request, params.toString(),2);
        getResult(response,a);
        return a;
    }

    //11提交核保任务
    @Override
    public ApiResult submitInsure(JSONObject params, HttpServletRequest request, ApiResult a) throws Exception {
        String response = new APIHttpClient()
                .post("/submitInsure", request, params.toString(),2);
        getResult(response,a);
        return a;
    }

    //13影像识别
    @Override
    @Transactional
    public ApiResult recognizeImage(JSONObject params, HttpServletRequest request, ApiResult a) throws Exception {
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";


        //将json对象转换为实体类
        ImageInfo imageInfo = gson.fromJson(params.toString(), ImageInfo.class);
        String imgUrl1 = FileUtil.uploadImgBase64(imageInfo.getImageContent(),path,request);

        imgUrl1 = imgUrl1.replace(this.path,basePath+"img/");
        imageInfo.setImageUrl(imgUrl1);
        imageInfo.setImageContent("");

        ReqImgDistinguish reqImgDistinguish = new ReqImgDistinguish();

        reqImgDistinguish.setImageInfo(imageInfo);



        //保存用户上传的图片信息
        UserImg userImg = new UserImg();
        //用户ID
        userImg.setUserId((Integer) request.getSession().getAttribute("userId"));
        //图片的用户类型
        userImg.setUserType("1");
        //图片的类型
        userImg.setImgType(imageInfo.getImageType());
        //图片的路径
        userImg.setImgUrl(imgUrl1);


        userImgMapper.insertSelective(userImg);


        String response = new APIHttpClient()
                .post("/recognizeImage", request, JSONUtil.toJSONString(reqImgDistinguish),2);

        //将返回的json字符串转换为实体类
        RespImgDistinguish respImgDistinguish = gson.fromJson(response, RespImgDistinguish.class);

        a = getResult(response,a);
        a.setData(respImgDistinguish);

        return a;
    }

    //14上传影响
    @Override
    public ApiResult uploadImage(ReqImgUpload reqImgUpload, HttpServletRequest request, ApiResult a) throws Exception {
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

        HttpSession session = request.getSession();
        String taskId = reqImgUpload.getTaskId();
        int userId = (Integer) request.getSession().getAttribute("userId");

        //循环将图片上传
        for (ImageInfo imageInfo : reqImgUpload.getImageInfos()){
            String imgUrl1 = FileUtil.uploadImgBase64(imageInfo.getImageContent(),path,request);

            imgUrl1 = imgUrl1.replace(this.path,basePath+"img/");
            imageInfo.setImageUrl(imgUrl1);
            imageInfo.setImageContent("");

            //保存用户上传的图片信息
            UserImg userImg = new UserImg();
            //用户ID
            userImg.setUserId(userId);
            //图片的用户类型
            userImg.setUserType("1");
            //图片的类型
            userImg.setImgType(imageInfo.getImageType());
            //图片的路径
            userImg.setImgUrl(imgUrl1);

            /**
             * 根据任务号检查影像信息是否存在
             * 若果存在，则更新userId和影像地址
             * 如果不存在，则添加信息
             *
             */
            if(taskId != null && taskId != ""){
                List<UserImg> userImgList =  userImgMapper.findAllUserImg(taskId);
                if(userImgList.size() == 0){  //如果数据库不存在此任务号的数据
                    userImgMapper.insertSelective(userImg);

                }else {
                    for (UserImg userImg1 : userImgList){
                        if(userImg1.getImgType().equals(userImg.getImgType())){
                            userImg1.setUserId(userId);
                            userImg1.setUserType("1");
                            userImg1.setImgUrl(imgUrl1);
                            userImg1.setCreateTime(new Date());
                            userImgMapper.updateByPrimaryKeySelective(userImg1);

                        }

                    }
                }
            }


        }



        String response = new APIHttpClient()
                .post("/uploadImage", request, JSONUtil.toJSONString(reqImgUpload),2);
        getResult(response,a);
        return a;
    }

    //15支付
    @Override
    public ApiResult pay(JSONObject params, HttpServletRequest request, ApiResult a) throws Exception {
        String response = new APIHttpClient()
                .post("/pay", request, params.toString(),2);
        getResult(response,a);
        return a;
    }

    //回调信息保存到数据库
    @Override
    public void finalState(String jsonStr) {
        Gson gson = new Gson();


        RespFinalState respFinalState = gson.fromJson(jsonStr,RespFinalState.class);
        respFinalState.setRespJson(jsonStr);

        boolean b = msgHandleService.addFinalState(respFinalState);
    }

    //查询车牌前缀
    @Override
    public String findLicensePlateByCityId(String cityId) {
        return dqLicenceLocationMapper.findLicensePlateByCityId(cityId) ;
    }


    /**
     * 返回结果的逻辑方法
     * **/
    public ApiResult getResult(String response, ApiResult a){

        if(response.contains("\"respCode\":\"00\"")){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
            a.setData(response);
        }else if(response.contains("\"respCode\":\"01\"")){
            a.setCode(ApiCode.FAIL);
            a.setMsg(ApiCode.FAIL_MSG);
            a.setData(response);
        }else{
            a.setCode(ApiCode.EXCEPTION);
            a.setMsg(ApiCode.EXCEPTION_MSG);
            a.setData(response);
        }
        return a;
    }

}


