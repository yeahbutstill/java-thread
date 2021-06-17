package net.greatsite.yeahbutstill.javathread;

public class MainApplication {

    public static void main(String[] args) {

        var nameThread = Thread.currentThread().getName();
        System.out.println(nameThread);

    }

}
