package net.greatsite.yeahbutstill.javathread;

public class DaemonApp {

    public static void main(String[] args) {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(3_000);
                System.out.println("Users");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();
    }

}
