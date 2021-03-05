package cn.jiangaifeng.validate;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.print.StreamPrintServiceFactory;
import javax.servlet.http.HttpServletRequest;

@Service
public class SmsService {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_SMS";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //发送短信验证码
    public void create(ServletWebRequest request){
        //生成验证码
        String code = RandomStringUtils.randomNumeric(6);
        ValidateCode validateCode = new ValidateCode(code, 60 );

        //保存校验码到session中
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, validateCode);

        //使用SMS SDK发送校验码到手机
        System.out.println("向手机"+"发送短信验证码"+code);
    }

}
