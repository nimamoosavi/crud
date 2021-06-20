package com.nicico.cost.crud.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Sort;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class is BaseService that you can extended your Service Class and you must create bean of it
 */
public interface GeneralService<S, R, I extends Serializable> {

    /**
     * @param s is the Request view Model that you can save it in Data Base
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save in Data Base
     */
    BaseDTO<R> save(@NotNull S s);

    /**
     * @param sList is the list of Request view Model that you can save it in Data Base
     * @return the list of Response of view model
     * @apiNote this method used for save batch in Data base
     */
    BaseDTO<List<R>> saveAll(List<S> sList);

    /**
     * @param s  is the Request view Model that you can save it in Data Base
     * @return the result of view Model
     * @apiNote this method used for update the Data
     */
    BaseDTO<R> update(@NotNull S s);

    /**
     * @param id is the incrementalId of data base
     * @return the result such as true or false
     * @apiNote this methode used for delete Data with the incremental Id
     */
    BaseDTO<Boolean> deleteById(@NotNull I id);

    /**
     * @param id is the incrementalId of data base
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from data base with the incremental Id of object
     */
    BaseDTO<R> findById(@NotNull I id);

    /**
     * @param id is the incrementalId of data base
     * @return the result such as true or false
     * @apiNote used for to know that this incremental Id is in Data Base Or Not
     */
    BaseDTO<Boolean> existsById(@NotNull I id);

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from data base that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    BaseDTO<List<R>> findAll();

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, List<Sort> orders);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    BaseDTO<PageDTO<List<R>>> findAllWithTotal(int page, int pageSize);

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    BaseDTO<PageDTO<List<R>>> findAllWithTotal(int page, int pageSize, List<Sort> orders);

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    BaseDTO<Long> count();
}
