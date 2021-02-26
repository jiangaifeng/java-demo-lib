package cn.jiangaifeng.controller;

import cn.jiangaifeng.bean.TestBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @RequestMapping("/1")
    public  String test1(){
        return "hello world";
    }


    //localhost:8080/test/2?id=1
    @ResponseBody
    @RequestMapping("/2")
    public  String test2(int id){
        String result;
        result = "简单类型" + id;
        return result;
    }

    //localhost:8080/test/3?ids=1&ids=2&ids=3
    @ResponseBody
    @RequestMapping("/3")
    public  String test3(int[] ids){
        String result;
        result = "简单类型数组:";
        for( int i = 0; i < ids.length; i++ ){
            result = result + ids[i] + ";";
        }
        return result;
    }

    //localhost:8080/test/4?id=1&name=jack&address=china
    @ResponseBody
    @RequestMapping("/4")
    public  String test4(TestBean testBean){
        String result;
        result = "简单对象类型" + testBean.toString();
        return result;
    }

    //localhost:8080/test/5?id=1&name=jack&address=china&level.id=1&level.name=A
    @ResponseBody
    @RequestMapping("/5")
    public  String test5(TestBean testBean){
        String result;
        result = "复杂对象类型" + testBean.toString();
        return result;
    }

    //localhost:8080/test/6?testBeans[0].id=1&testBeans[0].name=jack&testBeans[0].address=sd&testBeans[1].id=2&testBeans[1].name=tom&testBeans[1].address=js
    @ResponseBody
    @RequestMapping("/6")
    public  String test6(List<TestBean> testBeans){
        int size = testBeans.size();
        return "";
    }

    @ResponseBody
    @RequestMapping("/7")
    public  String test7(@RequestParam(value="id") int id ){
        String result="";
        result = result + id;
        return result;
    }

    @ResponseBody
    @RequestMapping("/8")
    public  String test8(TestBean testBean ){
        String result="";
        result = testBean.toString();
        return result;
    }
}
