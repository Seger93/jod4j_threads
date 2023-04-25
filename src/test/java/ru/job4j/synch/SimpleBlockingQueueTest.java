package ru.job4j.synch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class SimpleBlockingQueueTest {

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        Assertions.assertEquals(buffer, Arrays.asList(0, 1, 2, 3, 4));
    }

    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(2);
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> actual = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        for (Integer integer : expected) {
                            simpleBlockingQueue.offer(integer);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }

                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        actual.add(simpleBlockingQueue.poll());
                        actual.add(simpleBlockingQueue.poll());
                        actual.add(simpleBlockingQueue.poll());
                        actual.add(simpleBlockingQueue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        Assertions.assertEquals(actual, expected);
    }
}