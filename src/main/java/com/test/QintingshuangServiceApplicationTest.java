package com.test;

import com.qintingshuang.QintingshuangServiceApplication;
import com.qintingshuang.base.design.chainResponsibily.nodeControl.ResponsibityService;
import com.qintingshuang.base.design.chainResponsibily.outControl.ControlService;
import com.qintingshuang.base.design.factory.HpMouseFactory;
import com.qintingshuang.base.design.factory.MouseFactory;
import com.qintingshuang.base.design.strategic.PermissionService;
import com.qintingshuang.base.design.strategic.PermissionServiceImpl;
import com.qintingshuang.base.design.template.HpTask;
import com.qintingshuang.base.design.template.TaskRequest;
import com.qintingshuang.base.design.template.TaskResult;
import com.qintingshuang.web.common.EntityTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    Map<String, MouseFactory> map;

    @Autowired
    List<MouseFactory> list;

    @Autowired
    HpTask hpTask;

    /**
     * @Autowired根据类型注入    @Resource根据名称注入的
     */
    @Autowired
    EntityTest testBean;

    @Test
    public void responsibilyTest() {
        responsibily.mockedClient();
    }


    @Test
    public void nodeResponsibityService() {
        responsibityService.sendRequest();
    }

    @Test
    public void asyncTestService() throws InterruptedException {
        System.err.println(Thread.currentThread().getName());
        permissionService.asyncTest();
    }


    @Test
    public void exceptionTest() throws InterruptedException {
        String aaa = null;
        if (aaa.equals("22222")) {

        }
    }

    @Test
    public void MouseFactory() {
        MouseFactory mouseFactory = map.get("hpMouseFactory");
        mouseFactory.getMousePrintln();
        mouseFactory.getMouse();
        System.err.println(map.size() + list.size());
    }


    @Test
    public void HpTask() throws ExecutionException, InterruptedException {
        TaskRequest request = new TaskRequest();
        HashMap map = new HashMap<String, String>();
        map.put("31823515", "hahahhah");
        request.setParam(map);
//        Map<TaskRequest, TaskResult> taskMap= hpTask.execute(request);
//        System.err.println(taskMap);
    }

    @Test
    public void configTest(){


        System.err.println("获取对象：" + testBean.getUsername());
    }
}
