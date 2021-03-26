package com.qintingshuang.base.design.chainResponsibily.nodeControl;

import com.qintingshuang.base.design.chainResponsibily.noSpring.LeaveRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author qintingshuang
 * @create 2021-03-26 10:50
 * @description 直接主管审批
 **/
@Component
public class NodeDirectLeaderHandler extends NodeAbstractHandler {


    @Override
    protected void handlerRequest(LeaveRequest request) {

        if(request.getLeaveDays()<this.MIN){
            System.err.println("直接主管审批处理类");
            return;
        }

        if(!Objects.isNull(this.nextHandler)){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }
    }
}
