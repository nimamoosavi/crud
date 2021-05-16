package com.nicico.cost.crud.repository;

import com.nicico.cost.crud.domain.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GeneralRepository<T extends BaseEntity<I>, I extends Serializable> {
    Optional<T> save(T t);

    Optional<T> update(I id, T t);

    Optional<List<T>> saveAll(List<T> tList);

    Optional<T> findById(I id);

    Optional<List<T>> findAll();

    Optional<List<T>> findAll(int page, int pageSize);

    int count();

    Optional<Boolean> deleteById(I id);


}
