package ru.job4j.pool;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParallelAskindexTest {

    @Test
    public void whenElementString() {
        List<String> list1 = List.of("a", "b", "c", "d", "e", "f", "j", "k", "o", "y", "q", "t");
        ArrayList<Object> list = new ArrayList<>(list1);
        Integer exp = ParallelAskindex.sort(list, "e");
        assertThat(exp).isEqualTo(4);
    }

    @Test
    public void whenElementInt() {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        ArrayList<Object> list = new ArrayList<>(list1);
        Integer exp = ParallelAskindex.sort(list, 11);
        assertThat(exp).isEqualTo(10);
    }

    @Test
    public void whenAskIsLine() {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5, 6);
        ArrayList<Object> list = new ArrayList<>(list1);
        Integer exp = ParallelAskindex.sort(list, 2);
        assertThat(exp).isEqualTo(1);
    }
}