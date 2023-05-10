package app.ladderproject.crud.service;


import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can respond it from service
 * @param <I> is the type of database Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class is BaseService that you can extend your Service Class, and you must create bean of it
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
     * @return the list of Response of a view model
     * @apiNote this method used for save batch in Data base
     */
    BaseDTO<List<R>> saveAll(List<S> sList);

    /**
     * @param s is the Request view Model that you can save it in Data Base
     * @return the result of view Model
     * @apiNote this method used for update the Data
     */
    BaseDTO<R> update(@NotNull S s);

    /**
     * @param id is the incrementalId of database
     * @return the result such as true or false
     * @apiNote this methode used for deleted Data with the incrementalId
     */
    BaseDTO<Boolean> deleteById(@NotNull I id);

    /**
     * @param id is the incrementalId of database
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from a database with the incrementalId of object
     */
    BaseDTO<R> findById(@NotNull I id);

    /**
     * @param id is the incrementalId of database
     * @return the result such as true or false
     * @apiNote used for to know that this incrementalId is in Data Base Or Not
     */
    BaseDTO<Boolean> existsById(@NotNull I id);

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from a database you must know that the cost of this method is very expensive.
     * you can choose the method findListByPagination(...) and findByPagination(.) for fetch by pagination
     */
    BaseDTO<List<R>> findAll();

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from a database that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(.) for fetch by pagination
     */
    BaseDTO<List<R>> findAll(Query query);

    /**
     * @param page     is the number of pages you need to fetch
     * @param pageSize is a sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize);

    /**
     * @param page     is the number of pages you need to fetch
     * @param pageSize is a sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, Query query);


    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    BaseDTO<Long> count();

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    BaseDTO<Long> count(Query query);
}
