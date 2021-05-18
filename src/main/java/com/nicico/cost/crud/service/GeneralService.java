package com.nicico.cost.crud.service;


import com.nicico.cost.crud.domain.dto.PageDTO;
import com.nicico.cost.crud.domain.entity.BaseEntity;
import com.nicico.cost.crud.domain.view.BaseResVM;
import com.nicico.cost.crud.mapper.GeneralMapper;
import com.nicico.cost.crud.repository.GeneralRepository;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.exception.ExceptionEnum;
import com.nicico.cost.framework.service.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;

/**
 * @param <T> is the entity class that you must Extended to BaseEntity class {@link com.nicico.cost.crud.domain.entity.BaseEntity}
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class is BaseService that you can extended your Service Class and you must create bean of it
 */
public abstract class GeneralService<T extends BaseEntity<I>, S, R extends BaseResVM<I>, I extends Serializable> {

    /**
     * this class used for Repository layer that you must impl of method
     */
    @Autowired
    public GeneralRepository<T, I> generalRepository;
    @Autowired
    public ApplicationException applicationException;
    /**
     * general Mapper used MapStruct for cast Request view Model And Response View Model And Entity to each Other
     */
    @Autowired
    public GeneralMapper<T, S, R, I> generalMapper;


    /**
     * @param s is the Request view Model that you can save it in Data Base
     * @return the Response View Model that you must set in base class
     * @apiNote this method used for save in Data Base
     */
    @Transactional
    public BaseDTO<R> save(@NotNull S s) {
        T t = generalMapper.requestToEntity(s);
        T save = generalRepository.save(t).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOT_SAVE, HttpStatus.NOT_ACCEPTABLE)
        );
        return generalMapper.mapEntityToResponse(save);
    }

    /**
     * @param sList is the list of Request view Model that you can save it in Data Base
     * @return the list of Response of view model
     * @apiNote this method used for save batch in Data base
     */
    @Transactional
    public BaseDTO<List<R>> saveAll(List<S> sList) {
        List<T> tList = generalMapper.requestToEntity(sList);
        List<T> save = generalRepository.saveAll(tList).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOT_SAVE, HttpStatus.NOT_ACCEPTABLE)
        );
        return generalMapper.mapListEntityToResponse(save);
    }

    /**
     * @param s  is the Request view Model that you can save it in Data Base
     * @param id is the incrementalId of data base
     * @return the result of view Model
     * @apiNote this method used for update the Data
     */
    @Transactional
    public BaseDTO<R> update(@NotNull S s, @NotNull I id) {
        T t = generalMapper.requestToEntity(s);
        T tUpdate = generalRepository.update(id, t).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOT_UPDATE, HttpStatus.NOT_ACCEPTABLE)
        );
        return generalMapper.mapEntityToResponse(tUpdate);
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
        return generalMapper.mapEntityToResponse(t);
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
     * @apiNote thi method used for get all data from data base that you must know that the cost of this method is very expensive
     * you can choose the method findListByPagination(...) and findByPagination(..) for fetch by pagination
     */
    public BaseDTO<List<R>> getAll() {
        List<T> tList = generalRepository.findAll().orElse(Collections.emptyList());
        return generalMapper.mapListEntityToResponse(tList);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return BaseDTO<PageDTO < List < R>>> this methode return PageDTO that is all data in it
     */
    public BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize) {
        List<T> tList = generalRepository.findAll(page, pageSize).orElse(Collections.emptyList());
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
    public BaseDTO<PageDTO<List<R>>> findListByPagination(int page, int pageSize, List<Sort.Order> orders) {
        List<T> tList = generalRepository.findAll(page, pageSize, orders).orElse(Collections.emptyList());
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
    public BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize) {
        Long count = count().getData();
        List<T> tList = generalRepository.findAll(page, pageSize).orElse(Collections.emptyList());
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
    public BaseDTO<PageDTO<List<R>>> findByPagination(int page, int pageSize, List<Sort.Order> orders) {
        Long count = count().getData();
        List<T> tList = generalRepository.findAll(page, pageSize, orders).orElse(Collections.emptyList());
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
