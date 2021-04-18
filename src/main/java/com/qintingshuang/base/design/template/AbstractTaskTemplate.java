package com.qintingshuang.base.design.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qintingshuang.base.thread.pool.ThreadPoolExecutorDemo;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author qintingshuang
 * @create 2021-04-14 13:55
 * @description 抽象模板
 * 这个模板，利用线程池，搭整个业务执行框架
 * 可以做批量处理
 **/
public abstract class AbstractTaskTemplate<Request, Response> {


    /**
     * 加载任务
     *
     * @return
     */
    protected abstract List<Request> loadTask(TaskRequest request);


    /**
     * 任务执行
     *
     * @param request
     * @return
     */
    public Map<Request, Response> execute(TaskRequest request) throws ExecutionException, InterruptedException {

        List<Request> tasks = loadTask(request);
        Map<Request, Response> responseMap = new HashMap<>();
        Map<Request, Future<TaskResult>> futureTask = new HashMap<>();
        //任务执行
        for (Request task : tasks) {
            Future result = ThreadPoolExecutorDemo.threadPool.submit(() -> {
                executeTask( task);
            });
            futureTask.put(task, result);
        }
        //任务结果获取
        for (Map.Entry<Request, Future<TaskResult>> entry : futureTask.entrySet()) {
            Request req = entry.getKey();
            TaskResult result = entry.getValue().get();
            responseMap.put(req, (Response) result);
        }
        return responseMap;
    }


    /**
     * 自定义任务
     *
     * @param domain
     * @return
     */
    protected abstract Response executeTask(Request domain);



}
