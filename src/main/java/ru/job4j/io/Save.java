package ru.job4j.io;

import java.io.*;

public class Save {
    private final File file;

    public Save(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}