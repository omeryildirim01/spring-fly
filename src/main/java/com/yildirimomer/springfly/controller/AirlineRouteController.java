package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.AirlineRouteDto;
import com.yildirimomer.springfly.service.AirlineRouteServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */

@RestController
@RequestMapping(value = "/airlineroute")
@RequiredArgsConstructor
@Api(name = "AirlineRoute api",description = "demo controller for AirlineRoute module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "AirlineRoute api doc")
public class AirlineRouteController {

    /**
     * service impl object
     */
    private final AirlineRouteServiceImpl airlineRouteService;

    /**
     * to save object
     * @param airlineRouteDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<AirlineRouteDto> save(@ApiPathParam(name = "airlineRouteDto") @RequestBody AirlineRouteDto airlineRouteDto){
        Assert.notNull(airlineRouteDto.getName(), "Name mustn't be null");
        Assert.notNull(airlineRouteDto.getCode(), "Code mustn't be null");
        return ResponseEntity.ok(airlineRouteService.save(airlineRouteDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all AirlineRoute records")
    @ApiOperation(value = "Api method; to get all objects",notes = "this is a demo")
    public ResponseEntity<List<AirlineRouteDto>> getAll(){
        return ResponseEntity.ok(airlineRouteService.getAll());
    }

    /**
     * To get page items using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get AirlineRoute page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<AirlineRouteDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<AirlineRouteDto> resultPage = airlineRouteService.getAll(pageable);
        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by name , name is optional parameter
     * @param name
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find AirlineRoute items by using name, name is optional request parameter")
    @ApiOperation(value = "Api method; to find,search objects using by name param ",notes = "this is a demo")
    public ResponseEntity<List<AirlineRouteDto>> findByName(@ApiPathParam(name = "name") @RequestParam Optional<String> name) {
        List<AirlineRouteDto> resultList = airlineRouteService.findByName(name);
        return ResponseEntity.ok(resultList);
    }

    /**
     * To find by code , name is optional parameter
     * @param code
     * @return
     */
    @RequestMapping(value = "/searchbycode",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find AirlineRoute items by using code, name is optional request parameter")
    @ApiOperation(value = "Api method; to find,search objects using by code param ",notes = "this is a demo")
    public ResponseEntity<List<AirlineRouteDto>> findByCode(@ApiPathParam(name = "code") @RequestParam Optional<String> code) {
        List<AirlineRouteDto> resultList = airlineRouteService.findByCode(code);
        return ResponseEntity.ok(resultList);
    }

    /**
     * To find by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find item by id")
    @ApiOperation(value = "Api method; to find a record using by id ",notes = "to find by id which is an identity")
    public ResponseEntity<AirlineRouteDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        AirlineRouteDto result = airlineRouteService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
