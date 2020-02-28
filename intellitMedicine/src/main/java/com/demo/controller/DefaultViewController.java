package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这里配置默认可以打开的页面
 */
@Controller
public class DefaultViewController {
    /**
     * 打开首页
     * @return
     */
    @RequestMapping({"/","/index.do","/home.do"})
    public String homePage(){
        return "old/index";
    }
    /**
     * 打开登录页
     * @return
     */
    @RequestMapping("/loginPage.do")
    public String loginPage(){
        return "old/login";
    }

    /**
     * 打开注册页
     * @return
     */
//    @RequestMapping("/regPage.do")
//    public String regPage(){
//        return "old/UserReg";
//    }
}
