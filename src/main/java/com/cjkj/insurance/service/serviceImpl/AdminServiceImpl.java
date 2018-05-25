package com.cjkj.insurance.service.serviceImpl;

import com.cjkj.insurance.entity.*;
import com.cjkj.insurance.mapper.AdminMapper;
import com.cjkj.insurance.service.AdminService;
import com.cjkj.insurance.utils.ApiCode;
import com.cjkj.insurance.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登陆
     * @param username
     * @param password
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult login(String username, String password, HttpServletRequest request, ApiResult a) {
        Admin user = new Admin();
        user.setUsername(username);
        user.setPassword(password);
        Admin user1 = adminMapper.login(user);
        if(user1 == null){
            a.setCode(ApiCode.FAIL);
            a.setMsg(ApiCode.FAIL_MSG);
        }else {
            request.getSession().setAttribute("admin",user1);
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }
        return a;
    }

    /**
     * 修改管理员密码
     * @param uid
     * @param oldpassword
     * @param newpassword
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult change(String uid, String oldpassword, String newpassword, HttpServletRequest request, ApiResult a) {
        Object obj = request.getSession().getAttribute("admin");
        if(obj == null){
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        }else {
            Admin user = new Admin();
            user.setUsername(uid);
            user.setPassword(oldpassword);
            Admin user1 = adminMapper.login(user);
            if (user1 != null) {
                user1.setPassword(newpassword);
                int i = adminMapper.change(user1);
                if (i != 0) {
                    a.setCode(ApiCode.SUCCESS);
                    a.setMsg(ApiCode.SUCCESS_MSG);
                    //注销用户
                    request.getSession().invalidate();
                }
            } else {
                a.setCode(ApiCode.FAIL);
                a.setMsg(ApiCode.FAIL_MSG);
            }
        }
        return a;
    }

    /**
     * 查询订单详情
     * @param userID
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult findOrderById(Integer userID, HttpServletRequest request, ApiResult a) {
        //登陆验证
        Object obj = request.getSession().getAttribute("admin");
        if (obj == null) {
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        } else {
            Order order = new Order();
            //根据用户id查询车辆信息
            List<CarInfo> carInfoList = adminMapper.findCarInfoById(userID);
            //根据用户id查询车主信息
            List<CarOwner> carOwnerList = adminMapper.findCarOwnerById(userID);
            //根据用户id查询用户影像信息
            List<UserImg> userImgList = adminMapper.findUserImgById(userID);
            if (userImgList != null && userImgList.size() != 0 && userImgList.get(0) != null) {
                order.setUserImgList(userImgList);
            }
            if (carOwnerList != null && carOwnerList.size() != 0 && carOwnerList.get(0) != null) {
                order.setCarOwner(carOwnerList);
            }
            if (carInfoList == null || carInfoList.size() == 0 || carInfoList.get(0) == null) {
                a.setCode(ApiCode.SUCCESS);
                a.setMsg(ApiCode.SUCCESS_MSG);
            } else {
                order.setCarInfoList(carInfoList);
                for (CarInfo c : carInfoList) {
                    //根据任务号查询回调信息
                    List<RespMsg> respMsgList = adminMapper.findRespMsg(c.getTaskId());
                    if (respMsgList != null && respMsgList.size() != 0 && respMsgList.get(0) != null) {
                        order.setRespMsg(respMsgList);
                    }
                    for (RespMsg r : respMsgList) {
                        if ((c.getTaskId() != null || c.getTaskId().trim().length() != 0) &&
                                (r.getPrvId() != null || r.getPrvId().trim().length() != 0)) {
                            //根据任务号和供应商id查询保险配置
                            List<InsureInfo> insureInfoList = adminMapper.findInsureInfoByTaskId(c.getTaskId(), r.getPrvId());
                            if (insureInfoList != null) {
                                order.setInsureInfo(insureInfoList);
                            }
                            //根据任务号和供应商id查询险种信息
                            List<RiskKinds> riskKindsList = adminMapper.findRiskKinds(c.getTaskId(), r.getPrvId());
                            if (riskKindsList != null) {
                                order.setRiskKindsList(riskKindsList);
                            }
                            //根据任务号和供应商id查询配送信息
                            List<Delivery> deliveryList = adminMapper.findDelivery(c.getTaskId(), r.getPrvId());
                            if (deliveryList != null) {
                                order.setDeliveryList(deliveryList);
                            }
                            //根据任务号和供应商id查询核保补充数据项
                            List<InsureSupplys> insureSupplys = adminMapper.findInsureSupplys(c.getTaskId(), r.getPrvId());
                            if (insureSupplys != null) {
                                order.setSupplysList(insureSupplys);
                            }
                            //根据任务号和供应商id查询业务评级信息
                            List<ScoreRate> scoreRates = adminMapper.findScoreRate(c.getTaskId(), r.getPrvId());
                            if (scoreRates != null) {
                                order.setScoreRateList(scoreRates);
                            }
                        }
                    }
                }
                a.setCode(ApiCode.SUCCESS);
                a.setMsg(ApiCode.SUCCESS_MSG);
                a.setData(order);
            }
        }
        return a;
    }
}
