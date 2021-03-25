package com.qintingshuang.base.design.chainResponsibily.outControl;

import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-03-25 17:28
 * @description 次数限制校验
 **/
@Component
public class TimesFilter implements Filter {

    @Override
    public boolean filter(Task task) {
        //针对task的业务操作判断
        return false;
    }
}
