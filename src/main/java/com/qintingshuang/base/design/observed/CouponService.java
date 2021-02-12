package com.qintingshuang.base.design.observed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author qintingshuang
 * @create 2021-02-12 18:55
 * @description 优惠券监听
 **/
@Slf4j
@Service
public class CouponService {

    @Order(2)
    @EventListener
    public void coupon(UserRegisterEvent event) {
        log.info(event.getUserName()+"：发送优惠券");
    }
}
