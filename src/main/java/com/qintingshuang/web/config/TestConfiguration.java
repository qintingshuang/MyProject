package com.qintingshuang.web.config;

import com.qintingshuang.web.common.EntityTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author qintingshuang
 * @create 2021-04-23 10:57
 * @description configuration配置类
 **/
@RefreshScope
@Configuration
public class TestConfiguration {


    @Value("${web.config.test.name}")
    private  String name;

    /**
     * 这里的参数 其中的name 实例名
     * initMethod bean对象初始化方法，是返回类型类中的方法
     * destroyMethod bean对象销毁方法 ，是返回类型类中的方法
     *
     * Scope 五种作用域
     * 默认是 singleton  单例
     * prototype  多例 每次获取获取bean中都有一个新的实例
     * request  每一次http都有一个新的对象，同时该bean仅在当前 http request内有效
     * session  每一次http都有一个新的对象  同时该bean仅在当前 http session内有效
     * global session  类似http session ,不过它仅仅在基于portlet的web应用中才有意义
     *
     * 其中如果对象中有非静态变量时，会导致线程安全问题，共享资源的竞争，所以采用非单例的作用域，
     * 但是如果是prototype时，当请求越多，性能会下降的很多，创建对象，GC频繁，GC时长也会增加等问题。
     * 所以可以使用（request/session）
     *
     * @return
     */
    @Bean(name = "testBean",initMethod = "start",destroyMethod = "cleanUp")
    @Scope(value = "prototype")
    public EntityTest testBean(){
        return new EntityTest(name);
    }
}
