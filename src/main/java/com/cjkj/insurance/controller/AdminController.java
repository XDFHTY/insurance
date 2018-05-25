package com.cjkj.insurance.controller;

import com.cjkj.insurance.service.AdminService;
import com.cjkj.insurance.service.InsuranceService;
import com.cjkj.insurance.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/admin")
@Api(tags = "管理端接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("测试")
    @GetMapping("tset")
    public void test(){

    }

    /**
     * 1管理员登陆
     * @param username
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("1管理员登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String")
    })
    public ApiResult update(String username, String password, HttpServletRequest request){
        ApiResult a = new ApiResult();
        adminService.login(username,password,request,a);
        return a;
    }

    /**
     * 2修改密码
     * @param uid
     * @param oldpassword
     * @param newpassword
     * @param request
     * @return
     */
    @PostMapping("/change")
    @ResponseBody
    @ApiOperation("2修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "oldpassword",value = "旧密码",required = true,dataType = "String"),
            @ApiImplicitParam(name = "newpassword",value = "新密码",required = true,dataType = "String"),
    })
    public ApiResult update(String uid,String oldpassword, String newpassword, HttpServletRequest request){
        ApiResult a = new ApiResult();
        adminService.change(uid,oldpassword,newpassword,request,a);
        return a;
    }

    /**
     * 3查询订单详情
     * @param userID
     * @param request
     * @return
     */
    @PostMapping("/findOrderById")
    @ResponseBody
    @ApiOperation("3查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID",value = "查询订单",required = true,dataType = "int")
    })
    public ApiResult findOrderById(Integer userID, HttpServletRequest request){
        ApiResult a = new ApiResult();
        adminService.findOrderById(userID,request,a);
        return a;
    }
}
