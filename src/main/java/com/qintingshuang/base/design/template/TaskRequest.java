package com.qintingshuang.base.design.template;

import lombok.Data;

import java.util.Map;

/**
 * @author qintingshuang
 * @create 2021-04-14 14:22
 * @description 任务请求
 **/
@Data
public class TaskRequest {

    private Map<String,Object> param;
}
