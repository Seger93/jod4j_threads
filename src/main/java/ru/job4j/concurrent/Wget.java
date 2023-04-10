package ru.job4j.concurrent;

import java.io.BufferedInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final String fileName;
    private final int speed;

    public Wget(String url, String fileName, int speed) {
        this.url = url;
        this.fileName = fileName;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long startTimeStamp = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long endTimeStamp = System.currentTimeMillis();
                    if ((endTimeStamp - startTimeStamp) < 1000)
                        Thread.sleep(1000 - (endTimeStamp - startTimeStamp));
                    startTimeStamp = System.currentTimeMillis();
                    downloadData = 0;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не заданы аргументы/пустой массив");
        }
        if (!args[0].startsWith("https")) {
            throw new IllegalArgumentException("Некорректное имя файла");
        }
        if (Integer.parseInt(args[1]) < 0) {
            throw new IllegalArgumentException("Слишком малая скорость");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, fileName, speed));
        wget.start();
        try {
            wget.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}