package com.qintingshuang.base.design.template;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author qintingshuang
 * @create 2021-04-15 17:19
 * @description 惠普
 **/
@Component
public class HpTask extends AbstractTaskTemplate {


    @Override
    protected List loadTask(TaskRequest request) {
        //校验整理成要处理的dto

        //业务转换
        return null;

    }

    @Override
    public Map<TaskRequest, TaskResult> execute(TaskRequest request) throws ExecutionException, InterruptedException {
        return super.execute(request);
    }


    @Override
    protected Object executeTask(Object domain) {


        return null;
    }
}
