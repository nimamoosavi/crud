package app.ladderproject.crud.controller;

import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;
import app.ladderproject.crud.service.GeneralService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

import static app.ladderproject.core.config.general.GeneralStatic.*;

/**
 * @param <S> is request view model that you must create and added
 * @param <R> is the response view model that you can response it from service
 * @param <I> is the type of data base Identity class such as Long,String, ...
 * @author nima
 * @version 1.0.1
 * @apiNote this class is baseController that you can extended your rest controller and you must create bean of it
 */
public abstract class BaseController<S, R, I extends Serializable> {

    /**
     * General service used for all implementation of controller service
     */
    @Autowired
    GeneralService<S, R, I> generalService;

    /**
     * @param s is the object of request model
     * @return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
     * @apiNote this method save data to DataBase that you must implement in repository layer
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping
    public ResponseEntity<BaseDTO<R>> create(@Valid @RequestBody S s) {
        return new ResponseEntity<>(generalService.save(s), HttpStatus.CREATED);
    }

    /**
     * @param s is the object of request model
     * @return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
     * @apiNote this method save data to DataBase that you must implement in repository layer
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@Valid @RequestBody S s) {
        return new ResponseEntity<>(generalService.update(s), HttpStatus.OK);
    }

    /**
     * @param id is your IncrementalId of DataBase
     * @return ResponseEntity<BaseDTO < Boolean>> is the true or false result in BaseDTO pattern
     * @apiNote used for delete an entity from database
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @DeleteMapping
    public ResponseEntity<BaseDTO<Boolean>> deleteById(@Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.deleteById(id), HttpStatus.OK);
    }

    /**
     * @param id is your IncrementalId of DataBase
     * @return ResponseEntity<BaseDTO < R>> that R the view model you must add to controller
     * @apiNote this method used for get object from Identify number of database
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping
    public ResponseEntity<BaseDTO<R>> findByID(@Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.findById(id), HttpStatus.OK);
    }


    /**
     * @return ResponseEntity<BaseDTO < List < R>>> that R the view model you must add to controller
     * @apiNote used for getAll data from database , you must know that the cost of this method is high and you can used
     * findListByPagination Or findByPagination for fetch data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> findAll() {
        return new ResponseEntity<>(generalService.findAll(), HttpStatus.OK);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAll(@Valid @RequestParam Integer page, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(generalService.findAll(page, pageSize), HttpStatus.OK);
    }

    /**
     * @param query is the object for query string
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/all/query")
    public ResponseEntity<BaseDTO<List<R>>> findAllByQuery(@RequestBody Query query) {
        return new ResponseEntity<>(generalService.findAll(query), HttpStatus.OK);
    }

    /**
     * @param page     is the number of page you need to fetch
     * @param pageSize is the sizable page of data
     * @param query    is the object for query string
     * @return ResponseEntity<BaseDTO < PageDTO < List < R>>>> this methode return PageDTO that is all data in it
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/all/pagination/query")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> findAllByPaginationAndQuery(@Valid @RequestParam Integer page, @RequestParam Integer pageSize, @RequestBody Query query) {
        return new ResponseEntity<>(generalService.findAll(page, pageSize, query), HttpStatus.OK);
    }

    /**
     * @param id is your IncrementalId of DataBase
     * @return the boolean of result
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.existsById(id), HttpStatus.OK);
    }

    /**
     * @return the number of objects
     * @apiNote this controller used for the count of data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count() {
        return new ResponseEntity<>(generalService.count(), HttpStatus.OK);
    }

    /**
     * @return the number of objects
     * @apiNote this controller used for the count of data
     */
    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CORRELATION_ID, value = CORRELATION_ID, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = CLIENT_VERSION, value = CLIENT_VERSION, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/count/query")
    public ResponseEntity<BaseDTO<Long>> countByQuery(@RequestBody Query query) {
        return new ResponseEntity<>(generalService.count(query), HttpStatus.OK);
    }

}
