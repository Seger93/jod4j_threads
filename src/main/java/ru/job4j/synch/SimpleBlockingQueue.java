package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final Object monitor = this;

    public synchronized void offer(T value) {
        try {
            queue.add(value);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        monitor.notify();
    }

    public synchronized T poll() {
        while (queue.peek() == null) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return queue.peek();
    }
}