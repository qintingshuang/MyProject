package com.qintingshuang.base.design.chainResponsibily.noSpring;

/**
 * @author qintingshuang
 * @create 2021-03-25 16:33
 * @description 责任链测试
 **/
public class ResponsibityTest {

    public static void main(String[] args) {
        LeaveRequest leaveRequest = new LeaveRequest(16,"qin");
        AbstractLeaveHandler dept = new DeptLeaderLeaveHandler("部门经理");
        AbstractLeaveHandler direct = new DirectLeaderLeaveHandler("主管");
        AbstractLeaveHandler manager = new GManagerLeaveHandler("总经理");

        direct.setNextHandler(dept);
        dept.setNextHandler(manager);
        direct.handlerRequest(leaveRequest);
    }
}
