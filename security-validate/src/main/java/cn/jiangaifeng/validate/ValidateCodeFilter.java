package cn.jiangaifeng.validate;

import cn.jiangaifeng.handler.MyAuthentiationFailHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//校验验证码的过滤器
@Component
public class ValidateCodeFilter  extends OncePerRequestFilter{

    @Autowired
    private SmsService smsService;

    @Autowired
    private MyAuthentiationFailHandler authentiationFailHandler;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //只验证短信登录请求
        if(StringUtils.equalsIgnoreCase( httpServletRequest.getMethod(), "get")){
            if( pathMatcher.match(("/sms/login"), httpServletRequest.getRequestURI())){
                try{
                    smsService.validate( new ServletWebRequest(httpServletRequest, httpServletResponse));
                }catch( ValidateCodeException e){
                    authentiationFailHandler.onAuthenticationFailure( httpServletRequest, httpServletResponse, e );
                    return;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
