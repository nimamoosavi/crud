package com.nicico.cost.crud.service;


import com.nicico.cost.crud.mapper.GeneralMapper;
import com.nicico.cost.crud.repository.GeneralRepository;
import com.nicico.cost.framework.anotations.Log;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.enums.exception.ExceptionEnum;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote @Log {@link com.nicico.cost.framework.anotations.Log} Used For Log But if you need to Used It you must add Audit Library to Your Project
 * @apiNote this class is BaseService that you can extended your Service Class and you must create bean of it
 */
@Log
public abstract class GeneralService<T, S, R, I extends Serializable> {

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


    /**
     * @param s is the Request view Model that you can save it in Data Base
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save in Data Base
     */
    @Transactional
    public BaseDTO<R> save(@NotNull S s) {
        T t = generalMapper.requestToBaseObject(s);
        T save = generalRepository.save(t);
        return generalMapper.mapBaseObjectToResponse(save);
    }

    /**
     * @param sList is the list of Request view Model that you can save it in Data Base
     * @return the list of Response of view model
     * @apiNote this method used for save batch in Data base
     */
    @Transactional
    public BaseDTO<List<R>> saveAll(List<S> sList) {
        List<T> tList = generalMapper.requestToBaseObject(sList);
        List<T> save = generalRepository.saveAll(tList);
        return generalMapper.mapListBaseObjectToResponse(save);
    }

    /**
     * @param s  is the Request view Model that you can save it in Data Base
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
     * @param id is the incrementalId of data base
     * @return the result such as true or false
     * @apiNote this methode used for delete Data with the incremental Id
     */
    @Transactional
    public BaseDTO<Boolean> deleteById(@NotNull I id) {
        generalRepository.deleteById(id);
        return successCustomResponse(true);
    }


    /**
     * @param id is the incrementalId of data base
     * @return BaseDTO<R> is the result of find that you can give it the Response View Model
     * @apiNote this method used for fetch data from data base with the incremental Id of object
     */
    public BaseDTO<R> findById(@NotNull I id) {
        T t = generalRepository.findById(id).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOTFOUND, HttpStatus.NOT_FOUND)
        );
        return generalMapper.mapBaseObjectToResponse(t);
    }

    /**
     * @param id is the incrementalId of data base
     * @return the result such as true or false
     * @apiNote used for to know that this incremental Id is in Data Base Or Not
     */
    public BaseDTO<Boolean> existsById(@NotNull I id) {
        Optional<T> tOptional = generalRepository.findById(id);
        if (tOptional.isPresent())
            return successCustomResponse(true);
        else
            return successCustomResponse(false);
    }

    /**
     * @return BaseDTO<List < R>> the list of response view model Data
     * @apiNote this method used for get all data from data base that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    public BaseDTO<List<R>> findAll() {
        List<T> tList = generalRepository.findAll();
        return generalMapper.mapListBaseObjectToResponse(tList);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize) {
        List<T> tList = generalRepository.findAll(page, pageSize);
        List<R> rs = generalMapper.toResponseModel(tList);
        PageDTO<List<R>> pagination = PageDTO.<List<R>>builder().pageSize(pageSize).totalElement(null).object(rs).build();
        return successCustomResponse(pagination);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, String orders) {
        List<T> tList = generalRepository.findAll(page, pageSize, orders);
        List<R> rs = generalMapper.toResponseModel(tList);
        PageDTO<List<R>> pagination = PageDTO.<List<R>>builder().pageSize(pageSize).totalElement(null).object(rs).build();
        return successCustomResponse(pagination);
    }


    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    public BaseDTO<PageDTO<List<R>>> findAllWithTotal(int page, int pageSize) {
        Long count = count().getData();
        List<T> tList = generalRepository.findAll(page, pageSize);
        List<R> rs = generalMapper.toResponseModel(tList);
        PageDTO<List<R>> pagination = PageDTO.<List<R>>builder().pageSize(pageSize).totalElement(count).object(rs).build();
        return successCustomResponse(pagination);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param orders   orders is the list of fields and your direction such as Asc and Desc
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     * @apiNote this method call count method and return the count of data
     */
    public BaseDTO<PageDTO<List<R>>> findAllWithTotal(int page, int pageSize, String orders) {
        Long count = count().getData();
        List<T> tList = generalRepository.findAll(page, pageSize, orders);
        List<R> rs = generalMapper.toResponseModel(tList);
        PageDTO<List<R>> pagination = PageDTO.<List<R>>builder().pageSize(pageSize).totalElement(count).object(rs).build();
        return successCustomResponse(pagination);
    }

    /**
     * @return the number of data
     * @apiNote this method used for count of data objects
     */
    public BaseDTO<Long> count() {
        long count = generalRepository.count();
        return successCustomResponse(count);
    }


}
