package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        System.out.println("Loading ... |.");
        while (!Thread.currentThread().isInterrupted()) {
            var process = new char[]{'-', '\'', '|', '/' };
            for (char c : process) {
                System.out.print("\r load: " + c);
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            progress.interrupt();
        }
    }
}