package cn.jiangaifeng.configation;

import cn.jiangaifeng.handler.MyAuthentiationFailHandler;
import cn.jiangaifeng.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthentiationFailHandler myAuthentiationFailHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录。
                // http.httpBasic() //basic登录
                .loginPage("/authentication/require")
                .loginProcessingUrl("/api/auth/login")
                .successHandler( myAuthenticationSuccessHandler )
                .failureHandler( myAuthentiationFailHandler )
                .and()
                .authorizeRequests() // 对请求授权
                .antMatchers("authentication/require").permitAll()
                .anyRequest() // 任何请求
                .authenticated()// 需要身份认证
                .and()
                .csrf().disable();
    }
}
