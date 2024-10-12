package org.tbank.restkudago.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll();
    T save(T element);
    Optional<T> getById(Long id);
    void deleteById(Long id);
    void updateById(Long id, T element);
}
