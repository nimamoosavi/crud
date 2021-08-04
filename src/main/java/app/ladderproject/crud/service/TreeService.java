package app.ladderproject.crud.service;

import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;
import app.ladderproject.crud.domain.view.TreeReqVM;
import app.ladderproject.crud.domain.view.TreeResVM;

import java.io.Serializable;
import java.util.List;

/**
 * @param <S> is the Request View Model for All Service
 * @param <R> is the Response View Model For All Service
 * @param <I> is the Type Of IncrementalId , example Long / String /...
 * @author nima
 * @version 1.0.1
 * @apiNote this interface is the implementation of JpaRepository of Spring Data you can find know about it in {@link <a https://spring.io/projects/spring-data-jpa</a>}
 * and Used NameParameterJdbcTemplate And JdbcTemplate of Spring Framework For native Query.
 * you must create an interface and extended of it and generate a bean of your interface
 */
public interface TreeService<S extends TreeReqVM<I>, R extends TreeResVM<I>, I extends Serializable> extends GeneralService<S, R, I> {

    /**
     * @return The all Root Filed that we call them Parent
     */
    BaseDTO<List<R>> findAllRootParent();

    /**
     * @param query the Object you can set for where clause
     * @return BaseDTO<List < R>> is the List of Response View Model
     */
    BaseDTO<List<R>> findAllRootParent(Query query);

    /**
     * @param page     is the page number of pagination query
     * @param pageSize is the total element in pagination query
     * @param query    the Object you can set for where clause
     * @return BaseDTO<PageDTO < List < R>>> is the List of Response View Model with Pagination
     */
    BaseDTO<PageDTO<List<R>>> findAllRootParent(int page, int pageSize, Query query);

    /**
     * @param page     is the page number of pagination query
     * @param pageSize is the total element in pagination query
     * @return BaseDTO<PageDTO < List < R>>> is the List of Response View Model with Pagination
     */
    BaseDTO<PageDTO<List<R>>> findAllRootParent(int page, int pageSize);

    /**
     * @param pid is the parent id for fetch all child
     * @return BaseDTO<List < R>> is the List of Response View Model
     */
    BaseDTO<List<R>> findAll(I pid);

    /**
     * @param page     is the page number of pagination query
     * @param pageSize is the total element in pagination query
     * @param pid      is the parent id for fetch all child
     * @return BaseDTO<PageDTO < List < R>>> is the List of Response View Model with Pagination
     */
    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, I pid);

    /**
     * @param page     is the page number of pagination query
     * @param pageSize is the total element in pagination query
     * @param pid      is the parent id for fetch all child
     * @param query    the Object you can set for where clause
     * @return BaseDTO<PageDTO < List < R>>> is the List of Response View Model with Pagination
     */
    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, Query query, I pid);

}
