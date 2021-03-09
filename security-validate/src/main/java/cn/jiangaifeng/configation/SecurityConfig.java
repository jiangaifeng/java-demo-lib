package cn.jiangaifeng.configation;

import cn.jiangaifeng.validate.SmsCodeAuthenticationFilter;
import cn.jiangaifeng.validate.SmsCodeAuthenticationProvider;
import cn.jiangaifeng.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsCodeAuthenticationFilter smsCodeAuthenticationFilter;

    @Autowired
    private SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class)
            .authenticationProvider(smsCodeAuthenticationProvider)
            .addFilterAfter( smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class )
            .authorizeRequests() // 开始请求授权配置
            .antMatchers("/auth/code").permitAll()//忽略/auth/code
            .anyRequest() // 其他任何请求
            .authenticated()// 都需要身份认证
            .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter(){
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        try {
            smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return smsCodeAuthenticationFilter;
    }

    @Bean
    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider(){
        return new SmsCodeAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
