package com.qintingshuang.base.design.chainResponsibily.outControl;

import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-03-25 17:35
 * @description 风控拦截
 **/
@Component
public class RickFilter implements  Filter {

    @Override
    public boolean filter(Task task) {
        return false;
    }
}
