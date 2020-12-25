package thread.ThreadLocal;

import lombok.Data;

/**
 * @author qintingshuang
 * @create 2020-12-25 17:19
 * @description 没有使用ThreadLocal的使用
 **/
public class UnThreadLocal {


    @Data
    public static class Session {
        private String id;
        private String user;
        private String status;
    }


    public Session createSession() {
        return new Session();
    }


    public String getUser(Session session) {
        return session.getUser();
    }


    public String getStatus(Session session) {
        return session.getStatus();
    }

    public void setStatus(Session session, String status) {
        session.setStatus(status);
    }

    public static void main(String[] args) {
        new Thread(() -> {
            UnThreadLocal handler = new UnThreadLocal();
            Session session = handler.createSession();
            handler.getStatus(session);
            handler.getUser(session);
            handler.setStatus(session, "close");
            handler.getStatus(session);
        }).start();
    }


}
