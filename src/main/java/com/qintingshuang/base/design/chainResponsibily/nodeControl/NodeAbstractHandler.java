package com.qintingshuang.base.design.chainResponsibily.nodeControl;

import com.qintingshuang.base.design.chainResponsibily.noSpring.LeaveRequest;

/**
 * @author qintingshuang
 * @create 2021-03-26 10:41
 * @description spring控制的handler
 **/
public abstract class NodeAbstractHandler {

    //主管处理
    protected int  MIN=1;

    //经理
    protected int MIDDLE=3;

    //总经理
    protected int  MAX=30;

    protected  ThreadLocal<String>  handlerName;


    protected  NodeAbstractHandler nextHandler;


    protected  void  setNextHandler(NodeAbstractHandler handler){
        this.nextHandler=handler;
    }

    protected  abstract void   handlerRequest(LeaveRequest leaveRequest);
}
