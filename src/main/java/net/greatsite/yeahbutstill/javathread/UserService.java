package net.greatsite.yeahbutstill.javathread;

public class UserService {

    final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setUser(String user) {
        threadLocal.set(user);
    }

    public void doAction() {
        var user = threadLocal.get();
        System.out.println(user + " do action");
    }

}
