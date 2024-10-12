package org.tbank.restkudago.repository.impl;

import org.tbank.restkudago.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ConcurentHashMap<T> implements CrudRepository<T> {
    protected final ConcurrentHashMap<Long, T> map = new ConcurrentHashMap<>();
    protected Long lastIndex = 1L;

    @Override
    public List<T> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public abstract T save(T element);

    @Override
    public Optional<T> getById(Long id) {
        return Optional.of(map.get(id));
    }

    @Override
    public void deleteById(Long id) {
        this.map.remove(id);
    }

    @Override
    public void updateById(Long id, T element) {}
}
