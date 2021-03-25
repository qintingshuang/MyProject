package com.qintingshuang.base.design.chainResponsibily.outControl;

import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-03-25 17:27
 * @description 失效性校验
 **/
@Component
public class DurationFilter implements Filter {

    @Override
    public boolean filter(Task task) {

        // 针对task的业务操作判断
        return false;
    }
}
