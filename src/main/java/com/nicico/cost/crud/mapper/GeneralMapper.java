package com.nicico.cost.crud.mapper;

import com.nicico.cost.crud.domain.view.BaseResVM;
import com.nicico.cost.framework.domain.dto.BaseDTO;

import java.io.Serializable;
import java.util.List;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomListResponse;
import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;

/**
 * @param <T> is the entity class that you must Extended to BaseEntity class {@link com.nicico.cost.crud.domain.entity.BaseEntity}
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class used for map compile time and used is the protocol that implement by mapStruct library version 1.3.1.Final
 * you must create interface and extended it you must create interface and extended it
 * @apiNote is the protocol that implement by mapStruct library version 1.3.1.Final
 */
public abstract class GeneralMapper<T, S, R extends BaseResVM<I>, I extends Serializable> {

    /**
     * @param source is the source of request view model
     * @return the T is the entity
     * @apiNote method used for cast request to Entity
     */
    public abstract T requestToEntity(S source);

    /**
     * @param target the entity of object
     * @return the Request view model
     * @apiNote method used for cast Entity to Response View Model
     */
    public abstract S toRequestModel(T target);

    /**
     * @param sourceList the list of Request view Model
     * @return the list of Entity
     * @apiNote method used for cast List Response View Model to List Entity
     */
    public abstract Iterable<T> requestToEntity(Iterable<S> sourceList);

    /**
     * @param targetList the list of Response View Model
     * @return the List Of Request View Model
     * @apiNote method used for cast List Response View Model to List Request View Model
     */
    public abstract Iterable<S> toRequestModel(Iterable<R> targetList);

    /**
     * @param targetList the List Of Entity
     * @return the List of Response View Model
     * @apiNote method used for cast Iterable Of Entity to List Of Request View Model
     */
    public abstract Iterable<S> toRequestModels(Iterable<T> targetList);

    /**
     * @param source the Response View Model
     * @return the Entity Object
     * @apiNote method used for cast Response To Entity
     */
    public abstract T responseToEntity(R source);

    /**
     * @param target the Entity Object
     * @return the Response View Model
     * @apiNote method used for cast Entity to Response
     */
    public abstract R toResponseModel(T target);

    /**
     * @param sourceList the List Of Response View Model
     * @return the list Of Entity
     * @apiNote used for cast Iterable of Response to List Of Entity
     */
    public abstract Iterable<T> responseToEntity(Iterable<R> sourceList);

    /**
     * @param targetList the List of Entity
     * @return the List Of Response View Model
     * @apiNote used for cast to Iterable of Entity to List Of Response
     */
    public abstract Iterable<R> toResponseModel(Iterable<T> targetList);


    public BaseDTO<T> mapRequestToEntity(S s) {
        return successCustomResponse(requestToEntity(s));
    }

    public BaseDTO<Iterable<T>> mapListRequestToEntity(Iterable<S> s) {
        return successCustomListResponse(requestToEntity(s));
    }

    public BaseDTO<S> mapEntityToRequest(T t) {
        return successCustomResponse(toRequestModel(t));
    }

    public BaseDTO<Iterable<S>> mapListEntityToRequest(Iterable<T> t) {
        return successCustomResponse(toRequestModels(t));
    }

    public BaseDTO<R> mapEntityToResponse(T t) {
        return successCustomResponse(toResponseModel(t));
    }

    public BaseDTO<Iterable<R>> mapListEntityToResponse(Iterable<T> t) {
        return successCustomListResponse(toResponseModel(t));
    }

    public BaseDTO<T> mapResponseToEntity(R r) {
        return successCustomResponse(responseToEntity(r));
    }

    public BaseDTO<Iterable<T>> mapListResponseToEntity(Iterable<R> r) {
        return successCustomListResponse(responseToEntity(r));
    }

}
