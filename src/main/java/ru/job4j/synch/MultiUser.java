package ru.job4j.synch;

public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    System.out.println("Commit2");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                    System.out.println("Commit1");
                    System.out.println("Commit3");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}