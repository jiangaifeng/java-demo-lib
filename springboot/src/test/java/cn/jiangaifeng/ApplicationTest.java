package cn.jiangaifeng;

import cn.jiangaifeng.bean.*;
import cn.jiangaifeng.ioc.ann.MyBeanImport;
import cn.jiangaifeng.ioc.ann.MyCat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ContextConfiguration(locations = "classpath:ioc/demo.xml")
@Import(MyBeanImport.class)
public class ApplicationTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    Student student;

    @Autowired
    Dog dog;

    @Autowired
    User user;

    @Autowired
    @Qualifier("myCat")
    Animal animal;

    @Autowired
    Monkey monkey;

    @Autowired
    Bird bird;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    //测试IOC的XML方式
    //无参构造
    //有参构造
    //静态工厂方法
    @Test
    public void test1() {
        System.out.println(student.toString());
        //System.out.println(dog.getName());
    }

    //测试IOC的XML方式
    //工厂方法
    @Test
    public void test2() {
        //System.out.println(dog.getName());
    }

    //测试注解的@Bean
    @Test
    public void test3() {
        System.out.println(user.toString());
    }

    //测试注解的FactoryBean
    @Test
    public void test4() {
        System.out.println(animal.getName());
    }

    //测试注解的BeanDefinitionRegistryPostProcessor
    @Test
    public void test5() {
        System.out.println(monkey.getName());
    }

    //测试注解的ImportBeanDefinitionRegistar
    @Test
    public void test6() {
        System.out.println(bird.getName());
    }

}
