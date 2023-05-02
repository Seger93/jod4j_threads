package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelAskindexTest {

    @Test
    public void whenElementString() {
        String[] array = {"a", "b", "c", "d", "e", "f", "j", "k", "o", "y", "q", "t"};
        int exp = ParallelAskindex.searchEl(array, "e");
        assertThat(exp).isEqualTo(4);
    }

    @Test
    public void whenElementInt() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int exp = ParallelAskindex.searchEl(array, 2);
        assertThat(exp).isEqualTo(1);
    }

    @Test
    public void whenArraySizeIsFive() {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        int exp = ParallelAskindex.searchEl(array, 2);
        assertThat(exp).isEqualTo(1);
    }

    @Test
    public void whenElementNotFound() {
        Integer[] array = {1, 2, 3, 4, 5};
        int exp = ParallelAskindex.searchEl(array, 7);
        assertThat(exp).isEqualTo(-1);
    }
}