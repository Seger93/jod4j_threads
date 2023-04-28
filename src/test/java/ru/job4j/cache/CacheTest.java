package ru.job4j.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base base = new Base(0, 0);
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base);
        cache.add(base1);
        cache.add(base2);
        cache.update(new Base(0, 0));
        cache.update(new Base(0, 1));
        Base exp = cache.get(0);
        Assertions.assertThat(exp.getVersion()).isEqualTo(2);
    }

    @Test
    public void whenExp()  {
        Cache cache = new Cache();
        Base base = new Base(0, 1);
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        cache.add(base);
        cache.add(base1);
        cache.add(base2);
        assertThatThrownBy(() -> cache.update(new Base(0,3)))
                .isInstanceOf(OptimisticException.class)
                .message().isEqualTo("Неверная версия");
    }

    @Test
    public void whenGetIsNull() {
        Cache cache = new Cache();
        Base base = new Base(0, 1);
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        cache.add(base);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base1);
        Assertions.assertThat(cache.get(1)).isNull();
    }
}