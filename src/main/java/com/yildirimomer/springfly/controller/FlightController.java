package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.FlightDto;
import com.yildirimomer.springfly.service.FlightServiceImpl;
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
@RequestMapping(value = "/flight")
@RequiredArgsConstructor
@Api(name = "Flight api",description = "demo controller for Flight module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "Flight api doc")
public class FlightController {

    /**
     * service impl object
     */
    private final FlightServiceImpl flightService;

    /**
     * to save object
     * @param flightDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<FlightDto> save(@ApiPathParam(name = "flightDto") @RequestBody FlightDto flightDto){
        Assert.notNull(flightDto.getName(), "Name mustn't be null");
        Assert.notNull(flightDto.getCode(), "Code mustn't be null");
        return ResponseEntity.ok(flightService.save(flightDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all Flight records")
    @ApiOperation(value = "Api method; to get all objects",notes = "this is a demo")
    public ResponseEntity<List<FlightDto>> getAll(){
        return ResponseEntity.ok(flightService.getAll());
    }

    /**
     * To get page items by using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get Flight page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<FlightDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<FlightDto> resultPage = flightService.getAll(pageable);
        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by name , name is optional parameter
     * @param name
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find Flight items by using name, name is optional request parameter")
    @ApiOperation(value = "Api method; to find,search objects using by name param ",notes = "this is a demo")
    public ResponseEntity<List<FlightDto>> findByName(@ApiPathParam(name = "name") @RequestParam Optional<String> name) {
        List<FlightDto> resultList = flightService.findByName(name);
        return ResponseEntity.ok(resultList);
    }

    /**
     * To find by code , name is optional parameter
     * @param code
     * @return
     */
    @RequestMapping(value = "/searchbycode",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find Flight items by using code, name is optional request parameter")
    @ApiOperation(value = "Api method; to find,search objects using by code param ",notes = "this is a code")
    public ResponseEntity<List<FlightDto>> findByCode(@ApiPathParam(name = "code") @RequestParam Optional<String> code) {
        List<FlightDto> resultList = flightService.findByCode(code);
        return ResponseEntity.ok(resultList);
    }

    /**
     * To find by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find item by id")
    @ApiOperation(value = "Api method; to find a record using by id ",notes = "this is a code")
    public ResponseEntity<FlightDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        FlightDto result = flightService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
