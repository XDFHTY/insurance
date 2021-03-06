package com.cjkj.insurance.service;


import com.cjkj.insurance.entity.other.ReqCreateTaskA;
import com.cjkj.insurance.entity.other.ReqCreateTaskB;
import com.cjkj.insurance.entity.other.ReqImgUpload;
import com.cjkj.insurance.entity.other.ReqUpdateTask;
import com.cjkj.insurance.utils.ApiResult;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XD on 2017/12/8.
 */
public interface InsuranceService {

    public ApiResult getToken(HttpServletRequest request, ApiResult a) throws Exception;

    public ApiResult getAgreementAreas(HttpServletRequest request, String agreementProvCode, ApiResult a) throws Exception;

    public ApiResult getProviders(HttpServletRequest request,String insureAreaCode,ApiResult a) throws Exception;

    public ApiResult createTaskA(ReqCreateTaskA reqCreateTaskA, HttpServletRequest request, ApiResult a) throws Exception;

    public ApiResult createTaskB(ReqCreateTaskB reqCreateTaskB, HttpServletRequest request, ApiResult a) throws Exception;

    public ApiResult queryCarModelInfos(HttpServletRequest request, JSONObject params, ApiResult a) throws Exception;

    public ApiResult updateTask(HttpServletRequest request, ReqUpdateTask reqUpdateTask, ApiResult a) throws Exception;

    public ApiResult submitQuote(HttpServletRequest request, JSONObject params,ApiResult a) throws Exception;

    public ApiResult submitInsure(JSONObject params,HttpServletRequest request,ApiResult a) throws Exception;

    public ApiResult recognizeImage(JSONObject params,HttpServletRequest request,ApiResult a) throws Exception;

    public ApiResult uploadImage(ReqImgUpload reqImgUpload, HttpServletRequest request, ApiResult a) throws Exception;

    public ApiResult pay(JSONObject params,HttpServletRequest request,ApiResult a) throws Exception;

    public void finalState(String jsonStr);

    public String findLicensePlateByCityId(String cityId);


}
