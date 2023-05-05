package ru.job4j.synch;

import java.util.concurrent.Callable;

public class FutureTask {
    static Callable<String> task = () -> "Это задача типа Callable!";
    /*
     get() - бесконечно ожидает результата выполнения задачи (пока задача выполняется).

- get(long Timeout, TimeUnit unit) - ожидает результат в течение указанного количества времени.

Пока методы get() ждут результата, программа выполняется синхронно.

- cancel() - пытается отменить задачу. Не гарантирует, что задача точно отменится. Она может выполниться до конца.

- isCancelled() - возвращает true, если задача была успешно снята с выполнения.

- isDone() - возвращает true, если задача завершена (получено значение), либо перед этим был вызван метод cancel().

     */


    public static void main(String[] args) throws Exception {
        System.out.println(task.call());
    }
}