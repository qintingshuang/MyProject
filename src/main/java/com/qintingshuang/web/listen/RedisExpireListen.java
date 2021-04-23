package com.qintingshuang.web.listen;

import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-04-23 14:49
 * @description redis失效监听，执行定时业务操作
 * 还有死叉队列写法
 **/
@Component
public class RedisExpireListen extends KeyExpirationEventMessageListener {


    public RedisExpireListen(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    /**
     * 数据监听
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expireKey = message.toString();
        //业务代码
        System.err.println(expireKey);
    }
}
