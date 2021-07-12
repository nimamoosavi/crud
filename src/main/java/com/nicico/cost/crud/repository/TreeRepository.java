package com.nicico.cost.crud.repository;


import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote this class used for connect to data Base And you must implement this service in Your Library
 * @since 1.0.1
 */
public interface TreeRepository<T, I extends Serializable> extends GeneralRepository<T,I>{

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    List<T> findAllParent();

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    List<T> findAllParent(List<Sort> orders);

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    PageDTO<List<T>> findAllParent(int page, int pageSize, List<Sort> orders);

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    PageDTO<List<T>> findAllParent(int page, int pageSize);

    /**
     * @return the List Of Entities
     * @apiNote this methode uses for Fetch All Data
     */
    List<T> findAll(I pid);

    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Size of that you need to split Data
     * @return the Optional List Of Entity from Response Of Data Base
     */
    PageDTO<List<T>> findAll(int page, int pageSize, I pid);

    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Size of that you need to split Data
     * @param orders   is the list of fields and your direction such as Asc and Desc for Sorting
     * @return the Optional List Of Entity from Response Of Data Base
     */
    PageDTO<List<T>> findAll(int page, int pageSize, List<Sort> orders, I pid);
}
