package ru.job4j.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
            send(String.format("Notification %s to email %s", user.getUsername(), user.getEmail()),
                    String.format("Add a new event to %s", user.getUsername()), user.getEmail());
        });
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String email, String body) {
    }
}