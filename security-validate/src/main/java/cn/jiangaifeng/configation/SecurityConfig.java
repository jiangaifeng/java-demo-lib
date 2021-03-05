package cn.jiangaifeng.configation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthentiationFailHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 开始表单登录配置
            .loginPage("/authentication/require")//验证失败重定向地址
            .loginProcessingUrl("/api/auth/login")//表单登录url
            .successHandler( myAuthenticationSuccessHandler )//表单登录成功
            .failureHandler( myAuthentiationFailHandler )//表单登录失败
            .and()
            .authorizeRequests() // 开始请求授权配置
            .antMatchers("/auth/code").permitAll()//忽略/auth/code
            .anyRequest() // 其他任何请求
            .authenticated()// 都需要身份认证
            .and()
            .csrf().disable();
    }
}
