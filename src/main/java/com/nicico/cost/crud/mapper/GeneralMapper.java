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
public interface GeneralMapper<T, S, R extends BaseResVM<I>, I extends Serializable> {

    /**
     * @param source is the source of request view model
     * @return the T is the entity
     * @apiNote method used for cast request to Entity
     */
    T requestToEntity(S source);

    /**
     * @param target the entity of object
     * @return the Request view model
     * @apiNote method used for cast Entity to Response View Model
     */
    S toRequestModel(T target);

    /**
     * @param sourceList the list of Request view Model
     * @return the list of Entity
     * @apiNote method used for cast List Response View Model to List Entity
     */
    List<T> requestToEntity(List<S> sourceList);

    /**
     * @param targetList the list of Response View Model
     * @return the List Of Request View Model
     * @apiNote method used for cast List Response View Model to List Request View Model
     */
    List<S> toRequestModel(List<R> targetList);

    /**
     * @param targetList the List Of Entity
     * @return the List of Response View Model
     * @apiNote method used for cast List Of Entity to List Of Request View Model
     */
    List<S> toRequestModels(List<T> targetList);

    /**
     * @param source the Response View Model
     * @return the Entity Object
     * @apiNote method used for cast Response To Entity
     */
    T responseToEntity(R source);

    /**
     * @param target the Entity Object
     * @return the Response View Model
     * @apiNote method used for cast Entity to Response
     */
    R toResponseModel(T target);

    /**
     * @param sourceList the List Of Response View Model
     * @return the list Of Entity
     * @apiNote used for cast List of Response to List Of Entity
     */
    List<T> responseToEntity(List<R> sourceList);

    /**
     * @param targetList the List of Entity
     * @return the List Of Response View Model
     * @apiNote used for cast to List of Entity to List Of Response
     */
    List<R> toResponseModel(List<T> targetList);


    default BaseDTO<T> mapRequestToEntity(S s) {
        return successCustomResponse(requestToEntity(s));
    }

    default BaseDTO<List<T>> mapListRequestToEntity(List<S> s) {
        return successCustomListResponse(requestToEntity(s));
    }

    default BaseDTO<S> mapEntityToRequest(T t) {
        return successCustomResponse(toRequestModel(t));
    }

    default BaseDTO<List<S>> mapListEntityToRequest(List<T> t) {
        return successCustomResponse(toRequestModels(t));
    }

    default BaseDTO<R> mapEntityToResponse(T t) {
        return successCustomResponse(toResponseModel(t));
    }

    default BaseDTO<List<R>> mapListEntityToResponse(List<T> t) {
        return successCustomListResponse(toResponseModel(t));
    }

    default BaseDTO<T> mapResponseToEntity(R r) {
        return successCustomResponse(responseToEntity(r));
    }

    default BaseDTO<List<T>> mapListResponseToEntity(List<R> r) {
        return successCustomListResponse(responseToEntity(r));
    }

}
