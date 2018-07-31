package com.cjkj.insurance.service;

import com.cjkj.insurance.entity.Admin;
import com.cjkj.insurance.utils.ApiResult;
import com.cjkj.insurance.utils.domain.Pager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    //管理员登陆
    public ApiResult login(String adminName, String adminPass, HttpServletRequest request, ApiResult a);

    //修改管理员密码
    public ApiResult change(String uid, String oldpassword, String newpassword, HttpServletRequest request, ApiResult a);

    //查询订单详情
    public ApiResult findOrderById(Integer userID,String taskId, String prvId,
                                   String taskState, HttpServletRequest request,ApiResult a);

    //查询订单列表
    ApiResult findOrderList(HttpServletRequest request, ApiResult a);

    //添加管理员账号
    ApiResult insertAdmin(String adminName, String adminPass,HttpServletRequest request, ApiResult a);

    //删除用户
    ApiResult deleteAdmin(String adminName, HttpServletRequest request, ApiResult a);

    //用户个人查看订单列表
    ApiResult findOrder(Integer userId);

    //=================================================================================================

    //管理员查询订单列表
    public ApiResult findAllOrderBypager(Pager pager);

    //查询管理员列表
    public ApiResult findAllAdminByPager(Pager pager);

    //管理员修改自己的密码
    public ApiResult updatePass(String oldPass,String newPass,HttpServletRequest request);

    //管理员修改别人的密码
    public ApiResult updateOtherPass(Long adminId,String newPass);

    //新增管理员
    public ApiResult addAdmin(Admin admin);

    //删除管理员
    public ApiResult deleteAdmin(Long adminId);

    //查询订单详情
    public ApiResult findOrderInfo(String json);

}
