package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            Sums sumsI = new Sums();
            int row = 0;
            int column = 0;
            for (int j = column; j < matrix[0].length; j++) {
                row += matrix[i][j];
                column += matrix[j][i];
                sumsI.setRowSum(row);
                sumsI.setColSum(column);
            }
            sums[i] = sumsI;
        }
        return sums;
    }

    public static CompletableFuture<Sums> oneTask(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            int row = 0;
            int column = 0;
            for (int j = column; j < matrix[0].length; j++) {
                row += matrix[i][j];
                column += matrix[j][i];
                sums.setRowSum(row);
                sums.setColSum(column);
            }
            return sums;
        });
    }

    public static Sums[] anySum(int[][] matrix) throws InterruptedException, ExecutionException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = oneTask(matrix, i).get();
        }
        return sums;
    }
}