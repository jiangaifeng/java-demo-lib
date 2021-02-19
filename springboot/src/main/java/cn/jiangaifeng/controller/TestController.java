package cn.jiangaifeng.controller;

import cn.jiangaifeng.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @ResponseBody
    @RequestMapping("/1")
    public  String test1(){
        return "hello world";
    }

    //测试系统初始化器
    @ResponseBody
    @RequestMapping("/2")
    public  String test2(){
        return testService.test();
    }
}
