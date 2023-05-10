package app.ladderproject.crud.repository;


import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @param <I> is the type of database Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote this class used for connect to data Base, And you must implement this service in Your Library
 * @since 1.0.1
 */
public interface GeneralRepository<T, I extends Serializable> {
    /**
     * @param t the Entity View Model that you must Add To Data Base
     * @return the Optional Of Entity that saves it in a database
     * @apiNote this method used for save Data in Data base
     */
    T save(T t);

    /**
     * @param t the Entity View Model that you must Add To Data Base
     * @return the Optional Of Entity that saves it in a database
     * @apiNote this method used for Update data Base Object
     */
    T update(T t);

    /**
     * @param tList the list of Entity that you must save it in Data base
     * @return the Optional List Of Entity and their Ids
     * @apiNote used for save the batch of Data in Data base
     */
    List<T> saveAll(List<T> tList);


    /**
     * @param id the incrementalId of database Object
     * @return the Optional Of Entity that saves it in a database
     * @apiNote used for fetch Data By IncrementalId
     */
    Optional<T> findById(I id);

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    List<T> findAll();

    /**
     * @param query is the criteria for find in where Clause
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    List<T> findAll(Query query);


    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Size of that you needs to split Data
     * @return the Optional List Of Entity from Response Of Data Base
     */
    PageDTO<List<T>> findAll(int page, int pageSize);

    /**
     * @param page     the page number that you must fetch it
     * @param query    is the criteria for find in where Clause
     * @param pageSize the page Size of that you needs to split Data
     * @return the Optional List Of Entity from Response Of Data Base
     */
    PageDTO<List<T>> findAll(int page, int pageSize, Query query);


    /**
     * @return the Number Of data
     * @apiNote method used for get the count Of Data
     */
    long count();

    /**
     * @param query is the criteria for find in where Clause
     * @return the Number Of data
     * @apiNote method used for get the count Of Data
     */
    long count(Query query);

    /**
     * @param id is the incrementalId of Object that you need to remove it from Data Base
     * @throws RuntimeException has throw if Delete Method Not Acceptable
     * @apiNote Used for deleted Object From Incremental Id
     */
    void deleteById(I id);


}
