package ru.job4j.buffer;

import ru.job4j.synch.SimpleBlockingQueue;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ParallelSearch {

    public static void main(String[] args)  {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                           currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        Thread prod = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        );
        prod.start();
    }
}
