package com.cjkj.insurance.service.serviceImpl;

import com.cjkj.insurance.entity.*;
import com.cjkj.insurance.entity.other.*;
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
     *
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
        if (user1 == null) {
            a.setCode(ApiCode.FAIL);
            a.setMsg(ApiCode.FAIL_MSG);
        } else {
            request.getSession().setAttribute("admin", user1);
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }
        return a;
    }

    /**
     * 修改管理员密码
     *
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
        if (obj == null) {
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        } else {
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
     * @param taskId
     * @param prvId
     * @param taskState
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult findOrderById(Integer userID, String taskId, String prvId, String taskState, HttpServletRequest request, ApiResult a) {
        //登陆校验
        Object obj = request.getSession().getAttribute("admin");
        if (obj == null) {
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        }else {
            //查询订单详情基础信息
            List<RespOrderItme> list = adminMapper.findOrderItmesById(userID, taskId, prvId, taskState);
            //查询保险配置信息
            List<InsureInfo> insureInfoList = adminMapper.findInsureInfoById(taskId, prvId, taskState);
            //查询险种信息
            List<RiskKinds> riskKindsList = adminMapper.findRiskKindsById(taskId, prvId, taskState);

            //封装保险配置信息
            if (list != null && list.size() != 0 && list.get(0) != null) {
                for (RespOrderItme orderItme : list) {
                    InsureInfo insureInfo = new InsureInfo();
                    if (insureInfoList != null && insureInfoList.size() != 0
                            && insureInfoList.get(0)!=null) {
                        for (InsureInfo info : insureInfoList) {
                            //保险种类
                            String msgType = info.getMsgType();

                            if ("1".equals(msgType)) {
                                //1-交强险
                                EfcInsureInfo efcInsureInfo = new EfcInsureInfo();
                                efcInsureInfo.setStartDate(info.getStartDate());
                                efcInsureInfo.setEndDate(info.getEndDate());
                                efcInsureInfo.setAmount(info.getAmount());
                                efcInsureInfo.setPremium(info.getPremium());
                                efcInsureInfo.setPolicyNo(info.getPolicyNo());
                                efcInsureInfo.setDiscountRate(info.getDiscountRate());
                                insureInfo.setEfcInsureInfo(efcInsureInfo);
                            }
                            if ("2".equals(msgType)) {
                                //2-车船税
                                TaxInsureInfo taxInsureInfo = new TaxInsureInfo();
                                taxInsureInfo.setTaxFee(info.getTaxFee());
                                taxInsureInfo.setLateFee(info.getLateFee());
                                insureInfo.setTaxInsureInfo(taxInsureInfo);
                            }
                            if ("3".equals(msgType)) {
                                //3-商业险
                                BizInsureInfo bizInsureInfo = new BizInsureInfo();
                                bizInsureInfo.setStartDate(info.getStartDate());
                                bizInsureInfo.setEndDate(info.getEndDate());
                                bizInsureInfo.setPremium(info.getPremium());
                                bizInsureInfo.setDiscountRate(info.getDiscountRate());
                                bizInsureInfo.setPolicyNo(info.getPolicyNo());
                                bizInsureInfo.setNfcPremium(info.getNfcPremium());
                                if(riskKindsList != null && riskKindsList.size()!=0
                                        && riskKindsList.get(0)!=null) {
                                    bizInsureInfo.setRiskKinds(riskKindsList);
                                }
                                insureInfo.setBizInsureInfo(bizInsureInfo);
                            }
                        }
                        orderItme.setInsureInfo(insureInfo);
                    }
                }
            }
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
            a.setData(list);
        }
        return a;
    }



    /**
     * 查询订单列表
     *
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult findOrderList(HttpServletRequest request, ApiResult a) {
        //登陆验证
        Object obj = request.getSession().getAttribute("admin");
        if (obj == null) {
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        } else {
            //查询订单列表
            List<RespOrder> list = this.adminMapper.findOrderList();
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
            a.setData(list);
        }
        return a;
    }

}