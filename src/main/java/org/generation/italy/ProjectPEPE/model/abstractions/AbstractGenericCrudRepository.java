package org.generation.italy.ProjectPEPE.model.abstractions;

import java.util.Optional;

public interface AbstractGenericCrudRepository<T> {
    T create(T entity);
    Optional<T> findById(long id);
    void update(T entity);
    void deleteById(long id);
}
