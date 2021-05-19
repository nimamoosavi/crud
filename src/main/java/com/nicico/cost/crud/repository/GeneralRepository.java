package com.nicico.cost.crud.repository;

import com.nicico.cost.crud.domain.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @param <T> is the entity class that you must Extended to BaseEntity class {@link com.nicico.cost.crud.domain.entity.BaseEntity}
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote this class used for connect to data Base And you must implement this service in Your Library
 */
public interface GeneralRepository<T extends BaseEntity<I>, I extends Serializable> {
    /**
     * @param t the Entity View Model that you must Add To Data Base
     * @return the Optional Of Entity that save it in data base
     * @apiNote this method used for save Data in Data base
     */
    T save(T t);

    /**
     * @param id the incrementalId of data base Object
     * @param t  the Entity View Model that you must Add To Data Base
     * @return the Optional Of Entity that save it in data base
     * @apiNote this method used for Update data Base Object
     */
    T update(I id, T t);

    /**
     * @param tList the list of Entity that you must save it in Data base
     * @return the Optional List Of Entity and their Ids
     * @apiNote used for save the batch of Data in Data base
     */
    Iterable<T> saveAll(Iterable<T> tList);

    /**
     * @param id the incrementalId of data base Object
     * @return the Optional Of Entity that save it in data base
     * @apiNote used for fetch Data By IncrementalId
     */
    Optional<T> findById(I id);

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    List<T> findAll();

    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Size of that you need to split Data
     * @return the Optional List Of Entity from Response Of Data Base
     */
    List<T> findAll(int page, int pageSize);

    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Size of that you need to split Data
     * @param orders   is the list of fields and your direction such as Asc and Desc for Sorting
     * @return the Optional List Of Entity from Response Of Data Base
     */
    List<T> findAll(int page, int pageSize, String orders);

    /**
     * @return the Number Of data
     * @apiNote method used for get the count Of Data
     */
    long count();

    /**
     * @param id is the incrementalId of Object that you need too remove it from Data Base
     * @throws RuntimeException has throw if Delete Method Not Acceptable
     * @apiNote Used for delete Object From Incremental Id
     */
    void deleteById(I id);


}
