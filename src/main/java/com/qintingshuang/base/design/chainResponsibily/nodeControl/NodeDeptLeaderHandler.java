package com.qintingshuang.base.design.chainResponsibily.nodeControl;

import com.qintingshuang.base.design.chainResponsibily.noSpring.LeaveRequest;
import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-03-26 10:46
 * @description 部门经理
 **/
@Component
public class NodeDeptLeaderHandler extends NodeAbstractHandler {



    @Override
    protected void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() >this.MIN && request.getLeaveDays() <= this.MIDDLE){
            System.out.println("部门经理:" + handlerName.get() + ",已经处理;流程结束。");
            return;
        }
        if(null !=this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }
    }
}
