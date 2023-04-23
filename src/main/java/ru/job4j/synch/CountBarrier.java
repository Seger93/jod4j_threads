package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    @GuardedBy("this")
    private final Object monitor = this;
    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(5);
        Thread countThread = new Thread(() -> {
            for (int i = 0; i < barrier.total; i++) {
                barrier.count();
                System.out.println("countThread" + i + "Работает");
            }
        }
        );
        Thread waitThread = new Thread(() -> {
                barrier.await();
                System.out.println("waitThread" + "Ожидает");
        }
        );
        countThread.start();
        waitThread.start();
    }
}