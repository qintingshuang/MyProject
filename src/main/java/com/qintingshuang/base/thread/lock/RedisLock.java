package com.qintingshuang.base.thread.lock;

import io.lettuce.core.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * @author qintingshuang
 * @create 2021-03-22 15:19
 * @description redis的分布式锁
 **/
@Slf4j
@Component
public class RedisLock {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedissonClient redission;


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
     * <p>
     * 1.在vin码判断时，有分布式锁的引用
     * 2.接口幂等时，入参一秒阻塞，有的分布式锁
     *
     * @param key
     * @param requestId
     * @param expire
     * @return
     */
    public boolean tryLock(String key, String requestId, long expire) {
        try {
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
    public boolean releaseLock(String key, String requestId) {
        RedisCallback<Boolean> callback =
                (connection) -> connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1,
                        key.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")));
        try {
            return stringRedisTemplate.execute(callback);
        } catch (Exception e) {
            log.error("释放分布式锁发生异常", e);
        }
        return false;
    }


    /**
     * 分布式锁 业务代码原子性、顺序性
     * 不同节点来到着会阻塞住
     * 库存扣减时，会用到
     */
    private void redissonTest() {
        /**
         * 唯一账户的概念
         */
        String lockKey = "0000000006831523515";

        RReadWriteLock lock = redission.getReadWriteLock(lockKey);
        RLock wLock = lock.writeLock();
        try {
            wLock.lock();
            // 业务代码 这里不论操作报错、超时报错

        }finally {
            wLock.unlock();
        }

    }


    /**
     * 1.当有写锁时，第二条为读锁，也是会阻塞，已经测试，会超时；第二条写锁，会阻塞
     * 2.当有读锁时，第二条读锁时，不会阻塞（不互斥），第二条写锁，会阻塞
     *
     * @throws ParseException
     */

    public void redissionReadLock() throws ParseException {
        String accountCode = "0000000006831523515";
        RReadWriteLock lock = redission.getReadWriteLock(accountCode);
        RLock rLock = lock.readLock();
        rLock.lock(5000, TimeUnit.SECONDS);
        System.err.println("设置redis中读key，执行业务代码");
        RReadWriteLock lock1 = redission.getReadWriteLock(accountCode);
        lock1.readLock().lock();
        System.err.println("再次设置redis中读key，执行业务代码");
        RReadWriteLock lock2 = redission.getReadWriteLock(accountCode);
        RLock wLock = lock2.writeLock();
        wLock.lock();
        System.err.println("设置redis中写key，执行业务代码");
    }
}
