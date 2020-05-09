package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.AirportDto;
import com.yildirimomer.springfly.service.AirportServiceImpl;
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
@RequestMapping(value = "/airport")
@RequiredArgsConstructor
@Api(name = "Airport api",description = "demo controller for Airport module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "Airport api doc")
public class AirportController {

    /**
     * service impl object
     */
    private final AirportServiceImpl airportService;

    /**
     * to save object
     * @param airportDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save a new item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<AirportDto> save(@ApiPathParam(name = "airportDto") @RequestBody AirportDto airportDto){
        Assert.notNull(airportDto.getName(), "Name mustn't be null");
        return ResponseEntity.ok(airportService.save(airportDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all Airport records")
    @ApiOperation(value = "Api method; to get all objects",notes = "this is a demo")
    public ResponseEntity<List<AirportDto>> getAll(){
        return ResponseEntity.ok(airportService.getAll());
    }

    /**
     * To get page items using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get Airport page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<AirportDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<AirportDto> resultPage = airportService.getAll(pageable);

        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by name , name is optional parameter
     * @param name
     * @return
     */

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find Airport items by using name, name is optional request parameter")
    @ApiOperation(value = "Api method;  to find,search objects using by name param ",notes = "this is a demo")
    public ResponseEntity<List<AirportDto>> findByName(@ApiPathParam(name = "name") @RequestParam Optional<String> name) {
        List<AirportDto> resultList = airportService.findByName(name);
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
    public ResponseEntity<AirportDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        AirportDto result = airportService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
