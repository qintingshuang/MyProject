package com.qintingshuang.base.design.template;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author qintingshuang
 * @create 2021-04-15 17:19
 * @description 惠普任务
 **/
@Component
public class HpTask extends AbstractTaskTemplate<String> {


    @Override
    protected List<String> loadTask(TaskRequest request) {
        //校验整理成要处理的dto
        System.err.println("loadTask:" + Thread.currentThread().getName() + "拼接数据结构！！！！");
        //业务转换
        return new ArrayList() {{
            add("拼接数据结构！！！！");
        }};

    }

    @Override
    public Map<String, TaskResult> execute(TaskRequest request) throws ExecutionException, InterruptedException {
        return super.execute(request);
    }


    @Override
    protected TaskResult executeTask(String domain) {
        System.err.println("executeTask:" + Thread.currentThread().getName() + "数据落地！！！");
        return new TaskResult<String>(true, "测试完成", "任务完成");

    }
}
