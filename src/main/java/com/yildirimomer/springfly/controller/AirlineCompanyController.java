package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.AirlineCompanyDto;
import com.yildirimomer.springfly.service.AirlineCompanyServiceImpl;
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
@RequestMapping(value = "/airlinecompany")
@RequiredArgsConstructor
@Api(name = "AirlineCompany api",description = "demo controller for AirlineCompany module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "AirlineCompany api doc")
public class AirlineCompanyController {

    /**
     * service impl object
     */
    private final AirlineCompanyServiceImpl airlineCompanyService;

    /**
     * to save object
     * @param airlineCompanyDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save a new item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<AirlineCompanyDto> save(@ApiPathParam(name = "airlineCompanyDto") @RequestBody AirlineCompanyDto airlineCompanyDto){
        Assert.notNull(airlineCompanyDto.getName(), "Name mustn't be null");
        return ResponseEntity.ok(airlineCompanyService.save(airlineCompanyDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all AirlineCompany records")
    @ApiOperation(value = "Api method; to get all objects",notes = "this is a demo")
    public ResponseEntity<List<AirlineCompanyDto>> getAll(){
        return ResponseEntity.ok(airlineCompanyService.getAll());
    }

    /**
     * To get page items using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get AirlineCompany page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<AirlineCompanyDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<AirlineCompanyDto> resultPage = airlineCompanyService.getAll(pageable);
        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by name , name is optional parameter
     * @param name
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiMethod(description = "searching,  to find,search AirlineCompany items by using name, name is optional request parameter")
    @ApiOperation(value = "Api method;  to find,search objects using by name param ",notes = "this is a demo")
    public ResponseEntity<List<AirlineCompanyDto>> findByName(@ApiPathParam(name = "name") @RequestParam Optional<String> name) {
        List<AirlineCompanyDto> resultList = airlineCompanyService.findByName(name);
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
    public ResponseEntity<AirlineCompanyDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        AirlineCompanyDto result = airlineCompanyService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
