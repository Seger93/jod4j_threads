package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {

    @Test
    public void whenAsynchronously() throws ExecutionException, InterruptedException {
        int[][] table = new int[][]{
                {5, 8},
                {1, 1}
        };
        Sums[] res = RolColSum.anySum(table);
        Sums sums = new Sums();
        sums.setRowSum(13);
        sums.setColSum(6);
        Sums sums1 = new Sums();
        sums1.setColSum(9);
        sums1.setRowSum(2);
        Sums[] exp = {sums, sums1};
        assertThat(res).isEqualTo(exp);
    }

    @Test
    public void whenCalcLinear() {
        int[][] table = new int[][]{
                {5, 8},
                {1, 1}
        };
        Sums[] res = RolColSum.sum(table);
        Sums sums = new Sums();
        sums.setRowSum(13);
        sums.setColSum(6);
        Sums sums1 = new Sums();
        sums1.setColSum(9);
        sums1.setRowSum(2);
        Sums[] exp = {sums, sums1};
        assertThat(res).isEqualTo(exp);
    }
}