package ru.job4j.pool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelAskindex extends RecursiveTask<Integer> {

    private final List array;

    private final Object element;

    public ParallelAskindex(List array, Object element) {
        this.array = array;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if (array.size() <= 10) {
            for (Object o : array) {
                if (element.equals(o)) {
                    return array.indexOf(o);
                }
            }
        }
        int mid = array.size() / 2;
        ParallelAskindex left = new ParallelAskindex(array.subList(0, mid), element);
        ParallelAskindex right = new ParallelAskindex(array.subList(mid, array.size()), element);
        left.fork();
        right.fork();
        Object leftI = left.join();
        Object rightI = right.join();
        if (AskingElement.ask(leftI) < mid) {
            return AskingElement.ask(leftI);
        }
        return AskingElement.ask(rightI);
    }

    public static Integer sort(List array, Object el) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelAskindex(array, el));
    }
}