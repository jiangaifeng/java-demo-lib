package cn.jiangaifeng.ioc.ann;

import cn.jiangaifeng.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigation {
    @Bean("User")
    User getUser(){
        return new User();
    }
}
