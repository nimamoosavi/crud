package app.ladderproject.crud.service.impl;


import app.ladderproject.crud.domain.view.TreeReqVM;
import app.ladderproject.crud.domain.view.TreeResVM;
import app.ladderproject.crud.repository.TreeRepository;
import app.ladderproject.crud.service.TreeService;
import com.webold.framework.anotations.Log;
import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.domain.dto.PageDTO;
import com.webold.framework.packages.crud.view.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

import static com.webold.framework.service.GeneralResponse.successCustomResponse;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @implNote @Log {@link Log} Used For Log But if you need to Used It you must add Audit Library to Your Project
 * @apiNote this class is BaseService that you can extended your Service Class and you must create bean of it
 */
@Log
public abstract class TreeServiceImpl<T, S extends TreeReqVM<I>, R extends TreeResVM<I>, I extends Serializable> extends GeneralServiceImpl<T, S, R, I> implements TreeService<S, R, I> {

    /**
     * this class used for Repository layer that you must impl of method
     */
    @Autowired(required = false)
    public TreeRepository<T, I> treeRepository;


    public BaseDTO<List<R>> findAll(I pid) {
        List<T> all = treeRepository.findAll(pid);
        return generalMapper.mapListBaseObjectToResponse(all);
    }


    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, I pid) {
        PageDTO<List<T>> all = treeRepository.findAll(page, pageSize, pid);
        PageDTO<List<R>> listPageDTO = generalMapper.mapToPageDTOResponse(all);
        return successCustomResponse(listPageDTO);
    }

    public BaseDTO<PageDTO<List<R>>> findAll(int page, int pageSize, Query query, I pid) {
        PageDTO<List<T>> all = treeRepository.findAll(page, pageSize, query, pid);
        PageDTO<List<R>> listPageDTO = generalMapper.mapToPageDTOResponse(all);
        return successCustomResponse(listPageDTO);
    }

    @Override
    public BaseDTO<List<R>> findAllRootParent() {
        List<T> all = treeRepository.findAllParent();
        return generalMapper.mapListBaseObjectToResponse(all);
    }

    @Override
    public BaseDTO<List<R>> findAllRootParent(Query query) {
        List<T> all = treeRepository.findAllParent(query);
        return generalMapper.mapListBaseObjectToResponse(all);
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findAllRootParent(int page, int pageSize, Query query) {
        PageDTO<List<T>> parent = treeRepository.findAllParent(page, pageSize, query);
        PageDTO<List<R>> listPageDTO = generalMapper.mapToPageDTOResponse(parent);
        return successCustomResponse(listPageDTO);
    }

    @Override
    public BaseDTO<PageDTO<List<R>>> findAllRootParent(int page, int pageSize) {
        PageDTO<List<T>> parent = treeRepository.findAllParent(page, pageSize);
        PageDTO<List<R>> listPageDTO = generalMapper.mapToPageDTOResponse(parent);
        return successCustomResponse(listPageDTO);
    }
}
