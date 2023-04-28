package ru.job4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

class CASCountTest {

    @Test
    public void whenIncrement() throws InterruptedException {
        final CASCount casCount = new CASCount();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                casCount.increment();
            }
        });
        thread.start();
        AtomicInteger exp = new AtomicInteger();
        Thread thread1 = new Thread(() -> {
            exp.set(casCount.get());
        });
        thread1.start();
        thread.join();
        thread1.join();
        Assertions.assertEquals(exp.get(), 5);
    }
}