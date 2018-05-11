package com.cjkj.insurance.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@RequestMapping
@ApiIgnore
public class ToController {


    @GetMapping("/")
    public String toSwagger(){

        return "redirect:/swagger-ui.html";

    }
}
