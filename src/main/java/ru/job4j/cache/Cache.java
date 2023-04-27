package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, v) -> {
            Base baseGet = memory.get(v.getId());
            if (baseGet.getVersion() != v.getVersion()) {
                throw new OptimisticException("Неверная версия");
            }
            Base update = new Base(v.getId(), v.getVersion() + 1);
            update.setName(model.getName());
            return update;
        }) != null;
    }

    public void delete(Base model) {
       memory.remove(model.getId());
    }

    public Base get(int id) {
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        throw new IllegalArgumentException("Нет такого ключа");
    }
}