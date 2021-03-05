package cn.jiangaifeng.controller;

import cn.jiangaifeng.validate.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController {

    @Autowired
    SmsService smsService;

    @ResponseBody
    @RequestMapping("/auth/code")
    public String createCode(HttpServletRequest request, HttpServletResponse response){
        smsService.create( new ServletWebRequest(request) );
        return "success createCode";
    }
}
