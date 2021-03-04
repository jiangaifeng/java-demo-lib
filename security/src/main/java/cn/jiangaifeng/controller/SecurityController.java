package cn.jiangaifeng.controller;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    //当需要身份认证时，跳转到这里
    @RequestMapping("/authentication/require")
    @ResponseBody
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response ){
        return "访问的服务需要认证";
    }
}
