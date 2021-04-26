package com.qintingshuang.web.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qintingshuang
 * @create 2021-04-26 14:04
 * @description 定单生成工具类
 **/
@Slf4j
@Component
public class OrderUtils {

    private static final String SETTLE_CYCLE_NO_ = "STC";
    private static final String HFL_SETTLE_NO = "HFL";


    @Autowired
    RedisUtils redisUtils;

    public String generateSettleCycleNo() {
        log.info("开始生成结算周期号");
        String onlyNo = redisGenerateNo(SETTLE_CYCLE_NO_);
        log.info("生成唯一结算周期号, settleCycleNo:{}", SETTLE_CYCLE_NO_ + onlyNo);
        return SETTLE_CYCLE_NO_ + onlyNo;
    }


    /**
     * redis生成唯一编号
     * @param key
     * @return
     */
    private String redisGenerateNo(String key) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date day = new Date();
        String dayStr = formatter.format(day);
        //当天过期
        Date expireTime = getTodayEndTime();
        Long incrCount = redisUtils.incr(key, expireTime);
        String incrSequence = StringUtils.leftPad(incrCount.toString(), 10, "0");
        StringBuilder builder = new StringBuilder();
        String onlyNo = builder.append(dayStr).append("01").append(incrSequence).toString();
        return onlyNo;
    }

    /**
     * 失效时间，范围为今天截止
     * @return
     */
    private Date getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


}
