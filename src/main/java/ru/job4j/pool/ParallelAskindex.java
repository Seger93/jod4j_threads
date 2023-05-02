package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelAskindex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public ParallelAskindex(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if (from - to < 10) {
            return searchIndex();
        }
        int mid = (from + to) / 2;
        ParallelAskindex<T> left = new ParallelAskindex(array, from, mid, element);
        ParallelAskindex<T> right = new ParallelAskindex(array, mid, to, element);
        left.fork();
        right.fork();
        Integer leftI = left.join();
        Integer rightI = right.join();
        return Math.max(leftI, rightI);
    }

    private int searchIndex() {
        int rsl = -1;
        for (int i = from; i <= to; i++) {
            if (element.equals(array[i])) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public static <T> int searchEl(T[] array, T el) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelAskindex<T>(array, 0, array.length - 1, el));
    }
}