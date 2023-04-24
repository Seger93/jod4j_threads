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
            monitor.notify();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }
}