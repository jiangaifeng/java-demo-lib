package cn.jiangaifeng.validate;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.print.StreamPrintServiceFactory;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@Service
public class SmsService {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_SMS";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //生成短信验证码
    public void create(ServletWebRequest request){
        //生成验证码
        String code = RandomStringUtils.randomNumeric(6);
        ValidateCode validateCode = new ValidateCode(code, 60 );

        //保存校验码到session中
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, validateCode);

        //使用SMS SDK发送校验码到手机
        System.out.println("向手机"+"发送短信验证码"+code);
    }

    //校验请求中的验证码
    public void validate(ServletWebRequest servletWebRequest ){

        //从session中获取校验码
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute( servletWebRequest, SESSION_KEY_PREFIX );

        //从请求中获取验证码
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter( servletWebRequest.getRequest(),
                    "smsCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("请填写验证码");
        }

        if( codeInSession == null ){
            throw new ValidateCodeException("验证码不存在");
        }

        if( codeInSession.isExpried() ){
            //移除session
            sessionStrategy.removeAttribute( servletWebRequest,SESSION_KEY_PREFIX );
            throw new ValidateCodeException("验证码已经过期，请重新获取");
        }

        if( !StringUtils.equals(codeInSession.getCode(), codeInRequest )){
            throw new ValidateCodeException("验证码不正确");
        }

        //移除session
        sessionStrategy.removeAttribute( servletWebRequest,SESSION_KEY_PREFIX );
    }

}
