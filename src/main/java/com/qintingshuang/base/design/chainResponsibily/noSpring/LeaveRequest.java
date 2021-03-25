package com.qintingshuang.base.design.chainResponsibily.noSpring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qintingshuang
 * @create 2021-03-25 15:12
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    /**
     * 离开天数
     */
    private int  leaveDays;

    /**
     * 员工姓名
     */
    private  String  staffName;

}
