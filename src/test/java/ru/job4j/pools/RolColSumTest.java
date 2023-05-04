package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;


class RolColSumTest {

    @Test
    public void whenCalcColumn() throws ExecutionException, InterruptedException {
        int[][] table = new int[][]{
                {5, 8},
                {1, 1}
        };
        int exp = 9;
        Sums[] res = RolColSum.anySum(table);
        assertThat(res[1].getColSum()).isEqualTo(exp);
    }

    @Test
    public void whenCalcRow() throws ExecutionException, InterruptedException {
        int[][] table = new int[][]{
                {5, 8},
                {1, 1}
        };
        int exp = 13;
        Sums[] res = RolColSum.anySum(table);
        assertThat(res[0].getRowSum()).isEqualTo(exp);
    }

    @Test
    public void whenCalcLinear() {
        int[][] table = new int[][]{
                {5, 8},
                {1, 1}
        };
        int exp = 13;
        Sums[] res = RolColSum.sum(table);
        assertThat(res[0].getRowSum()).isEqualTo(exp);
    }
}