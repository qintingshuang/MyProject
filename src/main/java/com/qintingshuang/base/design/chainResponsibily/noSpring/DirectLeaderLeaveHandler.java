package com.qintingshuang.base.design.chainResponsibily.noSpring;

import java.util.Objects;

/**
 * @author qintingshuang
 * @create 2021-03-25 15:14
 * @description 直接主管审批处理类
 **/
public class DirectLeaderLeaveHandler extends  AbstractLeaveHandler {


    public  DirectLeaderLeaveHandler(String  name){
        this.handlerName=name;
    }


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
