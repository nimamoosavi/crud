package com.nicico.cost.crud.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Sort;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public interface GeneralService<S, R, I extends Serializable> {

    BaseDTO<R> save(@NotNull S s);

    BaseDTO<List<R>> saveAll(List<S> sList);

    BaseDTO<R> update(@NotNull S s);

    BaseDTO<Boolean> deleteById(@NotNull I id);

    BaseDTO<R> findById(@NotNull I id);

    BaseDTO<Boolean> existsById(@NotNull I id);

    BaseDTO<List<R>> findAll();

    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize);

    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, List<Sort> orders);

    BaseDTO<PageDTO<List<R>>> findAllWithTotal(int page, int pageSize);

    BaseDTO<PageDTO<List<R>>> findAllWithTotal(int page, int pageSize, List<Sort> orders);

    BaseDTO<Long> count();
}
