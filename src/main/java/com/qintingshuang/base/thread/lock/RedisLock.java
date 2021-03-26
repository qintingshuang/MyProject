package com.qintingshuang.base.thread.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author qintingshuang
 * @create 2021-03-22 15:19
 * @description redis的分布式锁
 **/
@Slf4j
public class RedisLock {

    @Resource
    StringRedisTemplate stringRedisTemplate;


    public static final String UNLOCK_LUA;

    /**
     * 释放锁脚本，原子操作
     */
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }



    /**
     * 尝试获取分布式锁
     *
     * 1.在vin码判断时，有分布式锁的引用
     * 2.接口幂等时，入参一秒阻塞，有的分布式锁
     *
     * @param key
     * @param requestId
     * @param expire
     * @return
     */
    public boolean tryLock(String key, String requestId, long expire) {
        try{
            RedisCallback<Boolean> callback =
                    (connection) -> connection.set(key.getBytes(Charset.forName("UTF-8")),
                            requestId.getBytes(Charset.forName("UTF-8")),
                            Expiration.from(expire, TimeUnit.SECONDS),
                            RedisStringCommands.SetOption.SET_IF_ABSENT);
            return stringRedisTemplate.execute(callback);
        } catch (Exception e) {
            log.error("设置分布式锁发生异常", e);
        }
        return false;
    }


    /**
     * 释放分布式锁
     *
     * @param key
     * @param requestId
     * @return
     */
    public boolean releaseLock(String key,String requestId) {
        RedisCallback<Boolean> callback =
                (connection) -> connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN ,1,
                        key.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")));
        try {
            return stringRedisTemplate.execute(callback);
        } catch (Exception e) {
            log.error("释放分布式锁发生异常", e);
        }
        return false;
    }
}
