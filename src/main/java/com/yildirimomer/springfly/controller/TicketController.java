package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.BookingDto;
import com.yildirimomer.springfly.domain.dto.CustomerDto;
import com.yildirimomer.springfly.domain.dto.TicketDto;
import com.yildirimomer.springfly.service.BookingServiceImpl;
import com.yildirimomer.springfly.service.TicketServiceImpl;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@RestController
@RequestMapping(value = "/ticket")
@RequiredArgsConstructor
@Api(name = "Ticket api",description = "demo controller for Ticket module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "Ticket api doc")
public class TicketController {

    /**
     * service impl object
     */
    private final TicketServiceImpl ticketService;
    private final BookingServiceImpl bookingService;

    /**
     * to save object
     * @param ticketDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<TicketDto> save(@ApiPathParam(name = "ticketDto") @RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all Ticket records")
    @ApiOperation(value = "Api method; to get all objects",notes = "this is a demo")
    public ResponseEntity<List<TicketDto>> getAll(){
        return ResponseEntity.ok(ticketService.getAll());
    }

    /**
     * To get page items by using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get Ticket page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<TicketDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<TicketDto> resultPage = ticketService.getAll(pageable);
        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by ticket number which is optional parameter
     * @param ticketNumber
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find Ticket items by using ticket number which is optional request parameter")
    @ApiOperation(value = "Api method; to find,search objects using by ticket number param ",notes = "this is a demo")
    public ResponseEntity<List<TicketDto>> findByName(@ApiPathParam(name = "ticketNumber") @RequestParam Optional<String> ticketNumber) {
        List<TicketDto> resultList = ticketService.findByTicketNumber(ticketNumber);
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
    public ResponseEntity<TicketDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        TicketDto result = ticketService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * to make a check in by booking id
     * @param bookingId
     * @return
     */
    @RequestMapping(value = "/checkin",method = RequestMethod.GET)
    @ApiMethod(description = "ticket check in")
    @ApiOperation(value = "Api method; check in a ticket ",notes = "this is a demo")
    public ResponseEntity<TicketDto> checkIn(@ApiPathParam(name = "bookingId") @RequestParam Long bookingId) {
        BookingDto bookingDto = bookingService.findById(bookingId);
        TicketDto ticketDto = new TicketDto();
        if (bookingDto !=null){
            ticketDto.setId(0L);
            ticketDto.setBooking(bookingDto);
            ticketDto.setGate(java.util.UUID.randomUUID().toString());
            ticketDto.setTicketNumber(java.util.UUID.randomUUID().toString());
            ticketDto.setCheckinDate(new Date());
            ticketDto= ticketService.save(ticketDto);
        }
        return ResponseEntity.ok(ticketDto);
    }


}