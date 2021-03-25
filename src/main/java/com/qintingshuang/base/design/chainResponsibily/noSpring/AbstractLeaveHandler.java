package com.qintingshuang.base.design.chainResponsibily.noSpring;

/**
 * @author qintingshuang
 * @create 2021-03-25 15:04
 * @description 请假责任链抽象处理类
 **/
public abstract class AbstractLeaveHandler {

    //主管处理
    protected int  MIN=1;

    //经理
    protected int MIDDLE=3;

    //总经理
    protected int  MAX=30;

    protected  String  handlerName;

    /**
     * 下一个领导
     */
    protected AbstractLeaveHandler nextHandler;


    protected  void  setNextHandler(AbstractLeaveHandler handler){
        this.nextHandler=handler;
    }

    protected abstract void  handlerRequest(LeaveRequest request);

}
