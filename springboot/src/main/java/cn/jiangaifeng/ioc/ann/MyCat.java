package cn.jiangaifeng.ioc.ann;

import cn.jiangaifeng.bean.Animal;
import cn.jiangaifeng.bean.Cat;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyCat implements FactoryBean<Animal> {
    @Override
    public Animal getObject() throws Exception {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }
}
