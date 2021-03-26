package com.qintingshuang.base.design.chainResponsibily.nodeControl;

import com.qintingshuang.base.design.chainResponsibily.noSpring.LeaveRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qintingshuang
 * @create 2021-03-26 11:04
 * @description 责任链服务服务
 **/
@Service
public class ResponsibityService implements InitializingBean {

    @Autowired
    NodeDeptLeaderHandler deptLeaderHandler;

    @Autowired
    NodeDirectLeaderHandler directLeaderHandler;

    @Autowired
    NodeManagerLeaveHandler managerLeaveHandler;



    @Override
    public void afterPropertiesSet() throws Exception {

        directLeaderHandler.handlerName=getHandlerName("主管");
        deptLeaderHandler.handlerName=getHandlerName("部门经理");
        managerLeaveHandler.handlerName=getHandlerName("总经理");
        directLeaderHandler.setNextHandler(deptLeaderHandler);
        deptLeaderHandler.setNextHandler(managerLeaveHandler);
    }



    public   void  sendRequest(){
        LeaveRequest leaveRequest = new LeaveRequest(16,"qin");
        directLeaderHandler.handlerRequest(leaveRequest);
    }



    private   ThreadLocal<String> getHandlerName(String  handlerName){
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set(handlerName);
        return stringThreadLocal;
    }


}
