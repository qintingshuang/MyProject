package com.qintingshuang.base.design.template;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qintingshuang
 * @create 2021-04-14 14:59
 * @description 任务响应
 **/
@Data
@AllArgsConstructor
public class TaskResult<T> {

    /**
     * 执行结果
     */
    private boolean result;

    /**
     * 执行信息
     */
    private  String message;

    /**
     * 响应结果
     */
    T response;

}
