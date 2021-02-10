package com.qintingshuang.base.thread.ThreadLocal;

import lombok.Data;

/**
 * @author qintingshuang
 * @create 2020-12-25 18:41
 * @description 使用本地线程池
 * <p>
 * 通过在线程内创建局部变量可实现每个线程都有自己的实例
 * 使用静态变量可以实现变量在方法间的共享
 * <p>
 * 同时满足变量在【线程间的隔离】与【方法间的共享】，ThreadLocal再合适不过了
 **/
public class UseThreadLocal {

    public static ThreadLocal<Session> session = new ThreadLocal<>();

    @Data
    public static class Session {
        private String id;
        private String user;
        private String status;
    }


    public void createSession() {
        session.set(new Session());
    }

    public String getUser() {
        return session.get().getUser();
    }

    public String getStatus() {
        return session.get().getStatus();
    }

    public void setStatus(String status) {
        session.get().setStatus(status);
    }

    public static void main(String[] args) {

        new Thread(() -> {
            UseThreadLocal useThreadLocal = new UseThreadLocal();
            useThreadLocal.getStatus();
            useThreadLocal.getUser();
            useThreadLocal.setStatus("close");
            useThreadLocal.getStatus();

        }).start();
    }


}
