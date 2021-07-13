package com.nicico.cost.crud.service;

import com.nicico.cost.crud.domain.view.TreeReqVM;
import com.nicico.cost.crud.domain.view.TreeResVM;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Query;

import java.io.Serializable;
import java.util.List;

public interface TreeService<S extends TreeReqVM<I>, R extends TreeResVM<I>, I extends Serializable> extends GeneralService<S, R, I> {

    BaseDTO<List<R>> findAllRootParent();

    BaseDTO<List<R>> findAllRootParent(Query query);

    BaseDTO<PageDTO<List<R>>> findAllRootParent(int page, int pageSize, Query query);

    BaseDTO<PageDTO<List<R>>> findAllRootParent(int page, int pageSize);

    BaseDTO<List<R>> findAll(I pid);

    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, I pid);

    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, Query query, I pid);

}
