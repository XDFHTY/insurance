package com.cjkj.insurance.service.serviceImpl;

import com.cjkj.insurance.entity.*;
import com.cjkj.insurance.entity.other.*;
import com.cjkj.insurance.mapper.AdminMapper;
import com.cjkj.insurance.service.AdminService;
import com.cjkj.insurance.utils.ApiCode;
import com.cjkj.insurance.utils.ApiResult;
import com.cjkj.insurance.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    public ApiResult login(String adminName, String adminPass, HttpServletRequest request, ApiResult a) {
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        String str = Md5Utils.MD5Encode(adminPass, "UTF-8", false);
        admin.setAdminPass(str);
        try {
            Admin user1 = adminMapper.login(admin);
            if (user1 == null) {
                a.setCode(ApiCode.userpwd_not_exist);
                a.setMsg(ApiCode.userpwd_not_exist_MSG);
            } else {
                request.getSession().setAttribute("admin", user1);
                a.setCode(ApiCode.SUCCESS);
                a.setMsg(ApiCode.SUCCESS_MSG);
            }
        }catch (Exception e){
            a.setCode(ApiCode.userpwd_not_exist);
            a.setMsg(ApiCode.userpwd_not_exist_MSG);
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
            user.setAdminName(uid);
            String str =  Md5Utils.MD5Encode(oldpassword, "UTF-8", false);
            user.setAdminPass(str);
            Admin user1 = adminMapper.login(user);
            if (user1 != null) {
                String newStr = Md5Utils.MD5Encode(newpassword, "UTF-8", false);
                user1.setAdminPass(newStr);
                int i = adminMapper.change(user1);
                if (i != 0) {
                    a.setCode(ApiCode.SUCCESS);
                    a.setMsg(ApiCode.SUCCESS_MSG);
                    //注销用户
                    request.getSession().removeAttribute("admin");
                }else {
                    a.setCode(ApiCode.FAIL);
                    a.setMsg(ApiCode.FAIL_MSG);
                }
            } else {
                a.setCode(ApiCode.pass_exist);
                a.setMsg(ApiCode.pass_exist_MSG);
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

    /**
     * 添加管理员账号
     *
     * @param adminName
     * @param adminPass
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult insertAdmin(String adminName, String adminPass, HttpServletRequest request, ApiResult a) {
        //登陆校验
        Object obj = request.getSession().getAttribute("admin");
        if (obj == null) {
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        } else {
            //封装admin
            Admin admin = new Admin();
            admin.setAdminName(adminName);
            String str = Md5Utils.MD5Encode(adminPass, "UTF-8", false);
            admin.setAdminPass(str);
            admin.setAdminType("1");
            admin.setAdminState("1");
            admin.setRoleId((long) 1);
            admin.setCreateTime(new Date());
            try {
                int i = adminMapper.insertAdmin(admin);
                if (i != 0) {
                    a.setCode(ApiCode.SUCCESS);
                    a.setMsg(ApiCode.SUCCESS_MSG);
                } else {
                    a.setCode(ApiCode.FAIL);
                    a.setMsg(ApiCode.FAIL_MSG);
                }
            } catch (Exception e) {
                a.setCode(ApiCode.FAIL);
                a.setMsg(ApiCode.FAIL_MSG);
            }

        }
        return a;
    }

    /**
     * 删除用户  非物理删除
     *
     * @param adminName
     * @param request
     * @param a
     * @return
     */
    @Override
    public ApiResult deleteAdmin(String adminName, HttpServletRequest request, ApiResult a) {
        //登陆校验
        Object obj = request.getSession().getAttribute("admin");
        if (obj == null) {
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        } else {
            //查询该用户
            Admin admin = adminMapper.findAdmin(adminName);
            if (admin != null) {
                if ("0".equals(admin.getAdminState())) {
                    //已经删除
                    a.setCode(ApiCode.delete_error);
                    a.setMsg(ApiCode.delete_error_MSG);
                } else {
                    //修改状态
                    admin.setAdminState("0");
                    //更新
                    try {
                        int i = adminMapper.updateAdmin(admin);
                        if (i != 0) {
                            //成功
                            a.setCode(ApiCode.SUCCESS);
                            a.setMsg(ApiCode.SUCCESS_MSG);
                        } else {
                            //失败
                            a.setCode(ApiCode.FAIL);
                            a.setMsg(ApiCode.FAIL_MSG);
                        }
                    } catch (Exception e) {
                        a.setCode(ApiCode.FAIL);
                        a.setMsg(ApiCode.FAIL_MSG);
                    }
                }
            } else {
                //无此用户
                a.setCode(ApiCode.no_admin_error);
                a.setMsg(ApiCode.no_admin_error_MSG);
            }

        }
        return a;
    }

    /**
     * 用户个人查询订单列表
     * @return
     */
    @Override
    public ApiResult findOrder(Integer userId) {
        ApiResult a = new ApiResult();
        List<Order> list = this.adminMapper.findOrder(userId);
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(list);
        return a;
    }


}