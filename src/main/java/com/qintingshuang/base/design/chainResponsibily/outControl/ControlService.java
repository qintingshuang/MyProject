package com.qintingshuang.base.design.chainResponsibily.outControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qintingshuang
 * @create 2021-03-25 17:37
 * @description 风控服务
 **/
@Service
public class ControlService {

    @Autowired
    List<Filter> filters;

    public void mockedClient(){
        Task task = new Task();
        for(Filter  f:filters){
            if(!f.filter(task)){
                return;
            }
        }

        // 校验通过
    }


}
