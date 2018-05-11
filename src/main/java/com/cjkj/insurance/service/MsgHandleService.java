package com.cjkj.insurance.service;


import com.cjkj.insurance.entity.ApiResult;
import com.cjkj.insurance.entity.other.ReqCreateTaskB;
import com.cjkj.insurance.entity.other.ReqUpdateTask;
import com.cjkj.insurance.entity.other.RespFinalState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MsgHandleService {

    //B接口用户报价数据保存
    public boolean addUserCarInfo(HttpSession session, ReqCreateTaskB reqCreateTaskB);

    //A接口修改后 用户报价数据保存
    public boolean updateUserMsg(HttpSession session, ReqUpdateTask reqUpdateTask);

    //提交报价任务成功后将 任务号更新到carInfo
    public boolean updateCarInfo(HttpSession session,String taskId);

    //提交报价任务成功后将任务号更新到CarOwner
    public boolean updateCarOwner(HttpSession session,String taskId);

    //保存回调信息
    public boolean addFinalState(RespFinalState respFinalState);

    //查询所有的报价信息
    public List<Map> findAllCallback(HttpSession session, Map map);
}
