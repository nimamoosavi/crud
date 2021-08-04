package app.ladderproject.crud.mapper;


import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.PageDTO;

import java.util.List;

import static app.ladderproject.core.service.GeneralResponse.successCustomListResponse;
import static app.ladderproject.core.service.GeneralResponse.successCustomResponse;


/**
 * @param <T> is the object class
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can Response it from service
 * @author nima
 * @version 1.0.1
 * @apiNote this class used for map compile time and used is the protocol that implement by mapStruct library version 1.3.1.Final
 * you must create interface and extended it you must create interface and extended it
 * @apiNote is the protocol that implement by mapStruct library version 1.3.1.Final
 */
public abstract class GeneralMapper<T, S, R> {

    /**
     * @param source is the source of request view model
     * @return the T is the Object
     * @apiNote method used for cast request to BaseObject
     */
    public abstract T requestToBaseObject(S source);

    /**
     * @param target the BaseObject of object
     * @return the Request view model
     * @apiNote method used for cast BaseObject to Response View Model
     */
    public abstract S toRequestModel(T target);

    /**
     * @param sourceList the list of Request view Model
     * @return the list of BaseObject
     * @apiNote method used for cast List Response View Model to List BaseObject
     */
    public abstract List<T> requestToBaseObject(List<S> sourceList);

    /**
     * @param targetList the list of Response View Model
     * @return the List Of Request View Model
     * @apiNote method used for cast List Response View Model to List Request View Model
     */
    public abstract List<S> toRequestModel(List<R> targetList);

    /**
     * @param targetList the List Of BaseObject
     * @return the List of Response View Model
     * @apiNote method used for cast Iterable Of BaseObject to List Of Request View Model
     */
    public abstract List<S> toRequestModels(List<T> targetList);

    /**
     * @param source the Response View Model
     * @return the BaseObject
     * @apiNote method used for cast Response To BaseObject
     */
    public abstract T responseToBaseObject(R source);

    /**
     * @param target the BaseObject Object
     * @return the Response View Model
     * @apiNote method used for cast BaseObject to Response
     */
    public abstract R toResponseModel(T target);

    /**
     * @param sourceList the List Of Response View Model
     * @return the list Of BaseObject
     * @apiNote used for cast Iterable of Response to List Of BaseObject
     */
    public abstract List<T> responseToBaseObject(List<R> sourceList);

    /**
     * @param targetList the List of BaseObject
     * @return the List Of Response View Model
     * @apiNote used for cast to Iterable of BaseObject to List Of Response
     */
    public abstract List<R> toResponseModel(List<T> targetList);

    public final PageDTO<List<R>> mapToPageDTOResponse(PageDTO<List<T>> pageDTO) {
        List<R> rList = mapListBaseObjectToResponse(pageDTO.getObject()).getData();
        return PageDTO.<List<R>>builder().object(rList)
                .pageSize(pageDTO.getPageSize())
                .totalPages(pageDTO.getTotalPages())
                .totalElement(pageDTO.getTotalElement()).build();
    }


    public final BaseDTO<T> mapRequestToBaseObject(S s) {
        return successCustomResponse(requestToBaseObject(s));
    }

    public final BaseDTO<List<T>> mapListRequestToBaseObject(List<S> s) {
        return successCustomListResponse(requestToBaseObject(s));
    }

    public final BaseDTO<S> mapBaseObjectToRequest(T t) {
        return successCustomResponse(toRequestModel(t));
    }

    public final BaseDTO<List<S>> mapListBaseObjectToRequest(List<T> t) {
        return successCustomResponse(toRequestModels(t));
    }

    public final BaseDTO<R> mapBaseObjectToResponse(T t) {
        return successCustomResponse(toResponseModel(t));
    }

    public final BaseDTO<List<R>> mapListBaseObjectToResponse(List<T> t) {
        return successCustomListResponse(toResponseModel(t));
    }

    public final BaseDTO<T> mapResponseToBaseObject(R r) {
        return successCustomResponse(responseToBaseObject(r));
    }

    public final BaseDTO<List<T>> mapListResponseToBaseObject(List<R> r) {
        return successCustomListResponse(responseToBaseObject(r));
    }

}
