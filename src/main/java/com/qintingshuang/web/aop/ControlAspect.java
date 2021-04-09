package com.qintingshuang.web.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author qintingshuang
 * @create 2021-04-09 11:12
 * @description controller的切面
 * 写两个切面，
 * <p>
 * 一个接口方法层面防止密等写法
 * <p>
 * 一个是controller层入参日志、接口出参打印，异常入库
 **/
@Slf4j
@Aspect
@Component
public class ControlAspect {

    @Autowired
    HttpServletRequest request1;

    /**
     * 线程标记
     */
    ThreadLocal<String> tag=new ThreadLocal<>();

    /**
     * 切点pointCut
     */
    @Pointcut("execution(* com.qintingshuang.web.controller..*(..))")
    public void executeController() {

    }


    /**
     * 请求报文打印
     *
     * @param joinPoint
     */
    @Before("executeController()")
    public void before(JoinPoint joinPoint) {
        tag.set(UUID.randomUUID().toString());
        JSONObject params = new JSONObject();
        //当前时间
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //获取当前请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 判定注入的request是否有并发的问题
        log.info("方法内的requet:{},注入的request1:{}", request.getMethod(), request1.getMethod());
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取该方法
        Method method = methodSignature.getMethod();
        //方法名称
        String methodName = method.getName();
        //参数名称
        String[] paramNames = methodSignature.getParameterNames();
        //参数值
        Object[] args = joinPoint.getArgs();
        int index = 0;
        for (String paramName : paramNames) {
            //先用index进行函数运算，之后再进行1=1+1的运算
            params.put(paramName, args[index++]);
        }
        //请求参数打印
        String paramStr = params.toJSONString();
        Enumeration<String> headerNames = request.getHeaderNames();
        //请求URL
        String url = request.getRequestURL().toString();
        //请求地址
        String ip = request.getRemoteAddr();
        //请求方式
        String httpMethod = request.getMethod();
        //请求头
        StringBuffer sb = new StringBuffer("{");
        do {
            String header = headerNames.nextElement();
            sb.append(header + ":" + request.getHeader(header));

        } while (headerNames.hasMoreElements());
        sb.append("}");
        log.info("标记{}，开始执行{}方法,开始时间:{},参数{},URL:{},地址:{},方式：{},请求头：{}",
                tag.get(),methodName, date, paramStr, url, ip, httpMethod, sb.toString());
    }

    /**
     * 接口返回
     * @param joinPoint
     * @param response
     */
    @AfterReturning(value = "executeController()", returning = "response")
    public void doAfterReturn(JoinPoint joinPoint, Object response) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //当前时间
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //方法名
        String methodName = method.getName();
        //响应
        String message = JSONObject.toJSONString(response);
        log.info("标记{},结束执行{}方法,结束时间:{},返回值为：{}",tag.get(), methodName, date, message);
    }

    /**
     * 异常捕获
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "executeController()",throwing = "e")
    public  void HandleThrowing(JoinPoint joinPoint,Exception e){
        //当前时间
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 方法名
        String methodName = methodSignature.getMethod().getName();
        log.info("标记{},结束执行{}方法,结束时间:{},错误码：{}",tag.get(), methodName, date,  e.getMessage());
    }
}
