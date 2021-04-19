package com.qintingshuang.base.design.template;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author qintingshuang
 * @create 2021-04-19 16:08
 * @description 戴尔任务
 **/
@Component
public class DellTask extends AbstractTaskTemplate<String> {



    @Override
    public Map<String, TaskResult> execute(TaskRequest request) throws ExecutionException, InterruptedException {
        return super.execute(request);
    }


    @Override
    protected List<String> loadTask(TaskRequest request) {
        return null;
    }


    @Override
    protected TaskResult executeTask(String domain) {
        return null;
    }
}
