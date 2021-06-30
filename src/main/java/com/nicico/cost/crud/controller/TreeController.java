package com.nicico.cost.crud.controller;

import com.nicico.cost.crud.domain.view.TreeReqVM;
import com.nicico.cost.crud.domain.view.TreeResVM;
import com.nicico.cost.crud.service.TreeService;
import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.domain.dto.PageDTO;
import com.nicico.cost.framework.packages.crud.view.Sort;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

import static com.nicico.cost.framework.config.general.GeneralStatic.*;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class is baseController that you can extended your rest controller and you must create bean of it
 */
public abstract class TreeController<S extends TreeReqVM<I>, R extends TreeResVM<I>, I extends Serializable> extends BaseController<S,R,I> {

    /**
     * General service used for all implementation of controller service
     */
    @Autowired
    TreeService<S, R, I> treeService;

    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/tree/root-parents")
    public ResponseEntity<BaseDTO<List<R>>> findAllRootParent() {
        return new ResponseEntity<>(treeService.findAllRootParent(), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/tree/root-parents/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAllRootParentByPagination(@RequestParam Integer page, @Valid @RequestParam Integer pageSize) {
        return new ResponseEntity<>(treeService.findAllRootParent(page, pageSize), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/tree/root-parents/sorts")
    public ResponseEntity<BaseDTO<List<R>>> findAllRootParentBySorts(@RequestBody List<Sort> sorts) {
        return new ResponseEntity<>(treeService.findAllRootParent(sorts), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/tree/root-parents/pagination/sorts")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAllRootParentByPagination(@RequestParam Integer page, @Valid @RequestParam Integer pageSize, @RequestBody List<Sort> sorts) {
        return new ResponseEntity<>(treeService.findAllRootParent(page,pageSize,sorts), HttpStatus.OK);
    }


    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/tree/child")
    public ResponseEntity<BaseDTO<List<R>>> findAllChildByParent(@RequestParam I parentId) {
        return new ResponseEntity<>(treeService.findAll(parentId), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/tree/child/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAllChildByParent(@RequestParam I parentId, @Valid @RequestParam Integer page, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(treeService.findAll(page, pageSize, parentId), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from data base , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/tree/child/pagination/sort")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAllChildByParent(@RequestParam I parentId, @Valid @RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody List<Sort> sorts) {
        return new ResponseEntity<>(treeService.findAll(page, pageSize, sorts, parentId), HttpStatus.OK);
    }


}
