package com.cjkj.insurance.controller;

import com.cjkj.insurance.entity.Admin;
import com.cjkj.insurance.service.AdminService;
import com.cjkj.insurance.service.InsuranceService;
import com.cjkj.insurance.utils.ApiCode;
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
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("1管理员登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "adminPass",value = "密码",required = true,dataType = "String")
    })
    public ApiResult update(String adminName, String adminPass, HttpServletRequest request){
        ApiResult a = new ApiResult();
        a = adminService.login(adminName,adminPass,request,a);
        return a;
    }

    /**
     * 2修改密码
     * @param request
     * @return
     */
    @PostMapping("/change")
    @ResponseBody
    @ApiOperation("2修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "oldPass",value = "旧密码",required = true,dataType = "String"),
            @ApiImplicitParam(name = "newPass",value = "新密码",required = true,dataType = "String"),
    })
    public ApiResult update(String adminName,String oldPass, String newPass, HttpServletRequest request){
        ApiResult a = new ApiResult();
        a = adminService.change(adminName,oldPass,newPass,request,a);
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
    @ApiOperation("4查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID",value = "用户id",required = true,dataType = "int"),
            @ApiImplicitParam(name = "taskId",value = "任务号",required = true,dataType = "String"),
            @ApiImplicitParam(name = "prvId",value = "供应商id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "taskState",value = "任务状态代码",dataType = "String"),
    })
    public ApiResult findOrderById(Integer userID,String taskId, String prvId,
                                   String taskState, HttpServletRequest request){
        ApiResult a = new ApiResult();
        a = adminService.findOrderById(userID,taskId,prvId,taskState,request,a);
        return a;
    }

    /**
     * 3查询订单列表
     * @param request
     * @return
     */
    @PostMapping("/findOrderList")
    @ResponseBody
    @ApiOperation("3查询订单列表")
    public ApiResult findOrderList(HttpServletRequest request){
        ApiResult a = new ApiResult();
        a = adminService.findOrderList(request,a);
        return a;
    }

    /**
     * 添加管理员
     * @param adminName
     * @param adminPass
     * @return
     */
    @PostMapping("/insertAdmin")
    @ResponseBody
    @ApiOperation("5添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "adminPass",value = "密码",required = true,dataType = "String"),
})
    public ApiResult insertAdmin(String adminName,String adminPass,
                                 HttpServletRequest request){
        ApiResult a = new ApiResult();
        a = adminService.insertAdmin(adminName,adminPass,request,a);
        return a;
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @PostMapping("/logOut")
    @ResponseBody
    @ApiOperation("6注销登录")
    public ApiResult logOut(HttpServletRequest request){
        ApiResult a = new ApiResult();
        request.getSession().removeAttribute("admin");
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        return a;
    }

    /**
     * 删除用户   非物理删除
     * @param adminName
     * @param request
     * @return
     */
    @DeleteMapping("/deleteAdmin")
    @ResponseBody
    @ApiOperation("6删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName",value = "用户名",required = true,dataType = "String"),
    })
    public ApiResult deleteAdmin(String adminName, HttpServletRequest request){
        ApiResult a = new ApiResult();
        a = adminService.deleteAdmin(adminName,request,a);
        return a;
    }



    /**
     * 用户个人查询订单列表
     * @param request
     * @return
     */
    @PostMapping("/findOrder")
    @ResponseBody
    @ApiOperation("用户个人查询订单列表")
    public ApiResult findOrder(HttpServletRequest request){
        ApiResult a = new ApiResult();
        //获取用户id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(userId == null){
            a.setCode(ApiCode.no_login);
            a.setMsg(ApiCode.no_login_MSG);
        }else {
            a = this.adminService.findOrder(userId);
        }
        return a;
    }
}
