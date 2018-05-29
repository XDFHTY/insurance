package com.cjkj.insurance.service;

import com.cjkj.insurance.utils.ApiResult;
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
}
