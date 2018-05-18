package com.cjkj.insurance.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
@Api(tags = "管理端接口")
public class AdminController {

    @ApiOperation("测试")
    @GetMapping("tset")
    public void test(){

    }
}
