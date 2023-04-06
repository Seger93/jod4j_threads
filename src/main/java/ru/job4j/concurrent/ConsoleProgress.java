package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public void run() {
        System.out.println("Loading ... |.");
        while (!Thread.currentThread().isInterrupted()) {
            var process = new char[]{'-', '\\', '|', '/' };
            for (char c : process) {
                System.out.print("\r load: " + c);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        progress.interrupt();
    }
}