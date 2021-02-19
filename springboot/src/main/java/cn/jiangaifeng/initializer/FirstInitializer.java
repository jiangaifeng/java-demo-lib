package cn.jiangaifeng.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class FirstInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key1","value1");
        MapPropertySource mapPropertySource = new MapPropertySource("firstInitializer",map);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("run FirstInitializer");
    }
}
