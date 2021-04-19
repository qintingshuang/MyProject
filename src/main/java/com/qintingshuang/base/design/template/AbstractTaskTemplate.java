package com.qintingshuang.base.design.template;

import com.qintingshuang.base.thread.pool.ThreadPoolExecutorDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author qintingshuang
 * @create 2021-04-14 13:55
 * @description 抽象模板
 * 这个模板，利用线程池，搭整个业务执行框架
 * 可以做批量处理
 **/
public abstract class AbstractTaskTemplate<T> {


    /**
     * 加载任务
     * 也可以作为拼接业务实体JSON
     *
     * @return
     */
    protected abstract List<T> loadTask(TaskRequest request);


    /**
     * 任务执行
     * @param request
     * @return
     */
    public Map<T, TaskResult> execute(TaskRequest request) throws ExecutionException, InterruptedException {
        System.err.println("execute:" + Thread.currentThread().getName());
        List<T> tasks = loadTask(request);
        Map<T, TaskResult> responseMap = new HashMap<>();
        Map<T, Future<TaskResult>> futureTask = new HashMap<>();
        //任务执行
        for (T task : tasks) {
            Future result = ThreadPoolExecutorDemo.threadPool.submit(() -> {
                System.err.println("任务执行");
                //如果需要返回值则使用return,如果不需要则不用return即可
                return executeTask(task);
            });
            futureTask.put(task, result);
        }
        //任务结果获取
        for (Map.Entry<T, Future<TaskResult>> entry : futureTask.entrySet()) {
            T req = entry.getKey();
            TaskResult result = entry.getValue().get();
            responseMap.put(req, result);
        }
        return responseMap;
    }

    /**
     * 自定义任务
     * 具体执行业务规则
     *
     * @param domain
     * @return
     */
    protected abstract TaskResult executeTask(T domain);
}
