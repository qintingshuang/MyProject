package com.test;

import com.qintingshuang.QintingshuangServiceApplication;
import com.qintingshuang.base.design.chainResponsibily.nodeControl.ResponsibityService;
import com.qintingshuang.base.design.chainResponsibily.outControl.ControlService;
import com.qintingshuang.base.design.strategic.PermissionService;
import com.qintingshuang.base.design.strategic.PermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author qintingshuang
 * @create 2021-03-25 17:53
 * @description 启动测试类
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QintingshuangServiceApplication.class)
public class QintingshuangServiceApplicationTest {

    @Autowired
    ControlService responsibily;

    @Autowired
    ResponsibityService responsibityService;

    @Autowired
    PermissionService permissionService;


    @Test
    public void responsibilyTest() {
        responsibily.mockedClient();
    }


    @Test
    public void nodeResponsibityService() {
        responsibityService.sendRequest();
    }

    @Test
    public  void  asyncTestService() throws InterruptedException {
        System.err.println(Thread.currentThread().getName());
        permissionService.asyncTest();
    }

}
