package com.qintingshuang.web.config;

import com.qintingshuang.web.listen.RedisExpireListen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author qintingshuang
 * @create 2021-04-23 15:16
 * @description redis配置类
 **/
@Configuration
public class RedisConfig {

    /**
     * 通配的__keyevent@*__:expired
     * 例子 ： __keyevent@0__:expired=chaijunkun
     * <p>
     * 在官方文档中，keyevent通道的格式永远是这样的：
     * __keyevent@<db>__:prefix
     * db 第几个库
     * prefix 句首
     */
    private static final String EXPIRETOPIC = "__keyevent@0__:expired=qin*";


    @Autowired
    private RedisExpireListen redisExpireListen;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(redisExpireListen, new PatternTopic(EXPIRETOPIC));
        return container;
    }


}
