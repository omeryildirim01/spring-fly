package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.CustomerDto;
import com.yildirimomer.springfly.service.CustomerServiceImpl;
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
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
@Api(name = "Customer api",description = "demo controller for Customer module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "Customer api doc")
public class CustomerController {

    /**
     * service impl object
     */
    private final CustomerServiceImpl customerService;

    /**
     * to save object
     * @param customerDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<CustomerDto> save(@ApiPathParam(name = "customerDto") @RequestBody CustomerDto customerDto){
        Assert.notNull(customerDto.getName(), "Name mustn't be null");
        return ResponseEntity.ok(customerService.save(customerDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all CustomerDto records")
    @ApiOperation(value = "Api method; to get all records",notes = "this is a demo")
    public ResponseEntity<List<CustomerDto>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    /**
     * To get page items by using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get Customer page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<CustomerDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<CustomerDto> resultPage = customerService.getAll(pageable);
        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by name , name is optional parameter
     * @param name
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find Customer items by using name, name is optional request parameter")
    @ApiOperation(value = "Api method; to find,search objects using by name param ",notes = "this is a demo")
    public ResponseEntity<List<CustomerDto>> findByName(@ApiPathParam(name = "name") @RequestParam Optional<String> name) {
        List<CustomerDto> resultList = customerService.findByName(name);
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
    public ResponseEntity<CustomerDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        CustomerDto result = customerService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}