package cn.jiangaifeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@SpringBootApplication
@ComponentScan("cn.jiangaifeng")
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args){
        System.out.println("server is running at 8080....");
        SpringApplication.run(Application.class,args);
    }
}
