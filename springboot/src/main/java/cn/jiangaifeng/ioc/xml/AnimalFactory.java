package cn.jiangaifeng.ioc.xml;

import cn.jiangaifeng.bean.Animal;
import cn.jiangaifeng.bean.Cat;
import cn.jiangaifeng.bean.Dog;

//工厂
public class AnimalFactory {
    //静态工厂
    public static Animal getAnimal(String type){
        if("dog".equals(type)){
            return new Dog();
        }
        else {
            return new Cat();
        }
    }

    //实例工厂
    public Animal getAnimal2(String type ){
        if("dog".equals(type)){
            return new Dog();
        }
        else {
            return new Cat();
        }
    }
}
