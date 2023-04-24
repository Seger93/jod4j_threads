package ru.job4j.synch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    public void whenAddAndPollThenSameReturned() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        List<Integer> list = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    queue.offer(1);
                }
        );
        Thread consumer = new Thread(
                () -> {
                    list.add(queue.poll());
                }
        );
        producer.start();
        producer.join();
        consumer.start();
        assertThat(list).isEqualTo(List.of(1));
    }
}