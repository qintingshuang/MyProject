package com.qintingshuang.web.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author qintingshuang
 * @create 2021-04-22 17:49
 * @description redis工具类
 **/
@Slf4j
public class RedisUtils {

    @Resource
    StringRedisTemplate stringRedisTemplate;


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 指定缓存失效时间 时长
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 如果不存在就是设置，并返回true
     * 如果存在就不设置，并返回false
     * @param key 索引
     * @param value 值
     * @return
     */
    public boolean setIfAbsent(String key,String value){
        return stringRedisTemplate.opsForValue().setIfAbsent(key,value);
    }


    /**
     * 如果存在就是设置，并返回true
     * 如果不存在就不设置，并返回false
     * @param key 索引
     * @param value 值
     * @return
     */
    public boolean setIfPresent(String key,String value){
        return stringRedisTemplate.opsForValue().setIfPresent(key,value);
    }

    /**
     * 根据key获取缓存
     *
     * @param key
     * @return
     */
    public Object getKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }



    /**
     * description: 写入缓存并设置过期时间
     * create time: 2019/7/2 17:17
     *
     * @return boolean
     * @Param: key
     * @Param: value
     * @Param: time, 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public boolean setKeyAndExpire(String key, Object value, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.opsForValue().set(key, (String) value, time, TimeUnit.SECONDS);
            } else {
                stringRedisTemplate.opsForValue().set(key, (String) value);
            }
        } catch (Exception e) {

            throw new RuntimeException("redis写入失败");
        }
        return true;
    }

    /**
     * 指定缓存失效时间  时间戳
     *
     * @param key
     * @param date
     * @return
     */
    public boolean expire(String key, Date date) {
        try {
            if (date != null) {
                stringRedisTemplate.expireAt(key, date);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean hmset(String key, Map<String, String> map, long time) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */

    public boolean hmset(String key, Map<String, String> map) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value jsonString
     * @return true 成功 false失败
     * 217
     */

    public boolean hset(String key, String item, String value) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean hset(String key, String item, String value, long expireTime) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            stringRedisTemplate.expire(key, expireTime, TimeUnit.DAYS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     * 161
     */

    public Object hget(String key, String item) {
        return stringRedisTemplate.opsForHash().get(key, item);
    }


    /**
     * 获取整个hash散列表
     * @param key
     * @return
     */
    public Object hentries(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }


    /**
     * 返回多个值
     *
     * @param key
     * @param items
     * @return
     */
    public Object multiGet(String key, List<Object> items) {
        return stringRedisTemplate.opsForHash().multiGet(key, items);
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 原子性操作后的值
     */
    public Long hincr(String key, String item, Long by) {
        Long result = 0L;
        try {
            result = stringRedisTemplate.opsForHash().increment(key, item, by);
        } catch (Exception e) {
            throw new RuntimeException("redis 操作异常");
        }
        return result;
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return stringRedisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key 键 不能为null
     * @return Set<Object> 返回key的数据列表
     */
    public Set<Object> hKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }


    /**
     * description: 写入缓存
     * create time: 2019/7/2 16:57
     * @Param: key
     * @Param: value
     * @return boolean
     */
    public boolean setKey(String key, Object value){
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        return true;
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */

    public boolean set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @param timeOut 失效时间 秒
     * @return
     */
    public boolean set(String key, String value,long timeOut) {
        try {
            stringRedisTemplate.opsForValue().set(key, value,timeOut,TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 添加或更新一个Hash<br>
     * <br>
     * 结果示例：<br>
     * Key即为Redis中的key<br>
     * <code>
     * {"hashKey": "value"}
     * </code>
     *
     * @param key     Key名
     * @param hashKey 一个Hash条目的key
     * @param value   一个Hash条目的Value
     */
    public void hSet(String key, String hashKey, Object value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, JSON.toJSONString(value));
    }

    public void hDel(String key, String item) {
        stringRedisTemplate.opsForHash().delete(key, item);
    }


    /**
     * create by: qintingshuang
     * description: 设置key在指定日期过期
     * create time: 2019/7/2 16:51
     * @Param: key
     * @Param: date
     * @return boolean
     */
    public boolean expireKeyAt(String key, Date date){
        return stringRedisTemplate.expireAt(key,date);
    }


    /**
     * 自增
     *
     * @param key
     * @return
     */
    public Long incr(String key){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, stringRedisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        return increment;
    }

    public void rPush(String key, String value) {
        stringRedisTemplate.opsForList().rightPush(key, value);
    }


    public void rPushAll(String key, List<Object> values) {
        stringRedisTemplate.opsForList().rightPush(key, String.valueOf(values.toArray()));
    }


    public String lPop(String key) {
        return (String) stringRedisTemplate.opsForList().leftPop(key);
    }

    public String rPop(String key) {
        return (String) stringRedisTemplate.opsForList().rightPop(key);
    }


    public Long remove(String key, String value) {
        return stringRedisTemplate.opsForList().remove(key, 1, value);
    }

    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }


}
