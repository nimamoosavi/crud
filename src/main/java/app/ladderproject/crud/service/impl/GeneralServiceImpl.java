package app.ladderproject.crud.service.impl;


import app.ladderproject.core.anotations.Log;
import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;
import app.ladderproject.core.service.exception.ApplicationException;
import app.ladderproject.core.service.exception.ServiceException;
import app.ladderproject.crud.mapper.GeneralMapper;
import app.ladderproject.crud.repository.GeneralRepository;
import app.ladderproject.crud.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static app.ladderproject.core.enums.exception.Exceptions.NOTFOUND;
import static app.ladderproject.core.service.GeneralResponse.successCustomResponse;


/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can respond it from service
 * @param <I> is the type of database Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote @Log {@link Log} Used For Log But if you need to Used It, you must add Audit Library to Your Project
 * @apiNote this class is BaseService that you can extend your Service Class, and you must create bean of it
 */
@Log
public abstract class GeneralServiceImpl<T, S, R, I extends Serializable> implements GeneralService<S, R, I> {

    /**
     * this class used for Repository layer that you must impl of method
     */
    @Autowired(required = false)
    public GeneralRepository<T, I> generalRepository;
    @Autowired
    public ApplicationException<ServiceException> applicationException;
    /**
     * general Mapper used MapStruct for cast Request view Model And Response View Model And BaseObject to each Other
     */
    @Autowired(required = false)
    public GeneralMapper<T, S, R> generalMapper;


    @Transactional
    public BaseDTO<R> save(@NotNull S s) {
        T t = generalMapper.requestToBaseObject(s);
        T save = generalRepository.save(t);
        return generalMapper.mapBaseObjectToResponse(save);
    }

    /**
     * @param sList is the list of Request view Model that you can save it in Data Base
     * @return the list of Response of a view model
     * @apiNote this method used for save batch in Data base
     */
    @Transactional
    public BaseDTO<List<R>> saveAll(List<S> sList) {
        List<T> tList = generalMapper.requestToBaseObject(sList);
        List<T> save = generalRepository.saveAll(tList);
        return generalMapper.mapListBaseObjectToResponse(save);
    }

    /**
     * @param s is the Request view Model that you can save it in Data Base
     * @return the result of view Model
     * @apiNote this method used for update the Data
     */
    @Transactional
    public BaseDTO<R> update(@NotNull S s) {
        T t = generalMapper.requestToBaseObject(s);
        T tUpdate = generalRepository.update(t);
        return generalMapper.mapBaseObjectToResponse(tUpdate);
    }

    /**
     * @param id is the incrementalId of database
     * @return the result such as true or false
     * @apiNote this methode used for deleted Data with the incremental I'd
     */
    @Transactional
    public BaseDTO<Boolean> deleteById(@NotNull I id) {
        generalRepository.deleteById(id);
        return successCustomResponse(true);
    }


    /**
     * @param id is the incrementalId of database
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from database with the incremental I'd of object
     */
    public BaseDTO<R> findById(@NotNull I id) {
        T t = generalRepository.findById(id).orElseThrow(
                () -> applicationException.createApplicationException(NOTFOUND, HttpStatus.NOT_FOUND)
        );
        return generalMapper.mapBaseObjectToResponse(t);
    }

    /**
     * @param id is the incrementalId of database
     * @return the result such as true or false
     * @apiNote used for to know that this incremental I'd be in Data Base Or Not
     */
    public BaseDTO<Boolean> existsById(@NotNull I id) {
        Optional<T> tOptional = generalRepository.findById(id);
        if (tOptional.isPresent())
            return successCustomResponse(Boolean.TRUE);
        else
            return successCustomResponse(Boolean.FALSE);
    }

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from a database that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    public BaseDTO<List<R>> findAll() {
        List<T> tList = generalRepository.findAll();
        return generalMapper.mapListBaseObjectToResponse(tList);
    }

    /**
     * @param page     is the number of pages you need to fetch
     * @param pageSize is a sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize) {
        PageDTO<List<T>> tList = generalRepository.findAll(page, pageSize);
        PageDTO<List<R>> listPageDTO = generalMapper.mapToPageDTOResponse(tList);
        return successCustomResponse(listPageDTO);
    }


    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    public BaseDTO<Long> count() {
        long count = generalRepository.count();
        return successCustomResponse(count);
    }

    /**
     * @param query is the where clause of a query?
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from a database that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    @Override
    public BaseDTO<List<R>> findAll(Query query) {
        List<T> all = generalRepository.findAll(query);
        return generalMapper.mapListBaseObjectToResponse(all);
    }

    /**
     * @param page     is the number of pages you need to fetch
     * @param pageSize is a sizable page of data
     * @param query    is the where clause of a query?
     * @return BaseDTO<List < R>> the list of response view model Data
     */
    @Override
    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, Query query) {
        PageDTO<List<T>> all = generalRepository.findAll(page, pageSize, query);
        PageDTO<List<R>> listPageDTO = generalMapper.mapToPageDTOResponse(all);
        return successCustomResponse(listPageDTO);
    }

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    @Override
    public BaseDTO<Long> count(Query query) {
        long count = generalRepository.count(query);
        return successCustomResponse(count);
    }
}
