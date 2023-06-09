package ru.job4j.pool;

import ru.job4j.synch.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        while (threads.size() <= size) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
