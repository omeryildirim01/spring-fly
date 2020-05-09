package com.yildirimomer.springfly.controller;

import com.yildirimomer.springfly.domain.dto.AirportDto;
import com.yildirimomer.springfly.domain.dto.BookingDto;
import com.yildirimomer.springfly.domain.dto.CustomerDto;
import com.yildirimomer.springfly.domain.dto.FlightDto;
import com.yildirimomer.springfly.domain.model.CreditCardType;
import com.yildirimomer.springfly.service.BookingServiceImpl;
import com.yildirimomer.springfly.service.CustomerServiceImpl;
import com.yildirimomer.springfly.service.FlightServiceImpl;
import com.yildirimomer.springfly.tools.CardUtil;
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

import javax.validation.constraints.NotNull;
import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@RestController
@RequestMapping(value = "/booking")
@RequiredArgsConstructor
@Api(name = "Booking api",description = "demo controller for Booking module", stage = ApiStage.BETA)
@io.swagger.annotations.Api(value = "Booking api doc")
public class BookingController {

    /**
     * service impl object
     */
    private final BookingServiceImpl bookingService;
    private final CustomerServiceImpl customerService;
    private final FlightServiceImpl flightService;

    /**
     * to save object
     * @param bookingDto
     * @return saved object
     */
    @PostMapping
    @ApiMethod(description = "to save item")
    @ApiOperation(value = "Api method; to save an object",notes = "this is a demo")
    public ResponseEntity<BookingDto> save(@ApiPathParam(name = "bookingDto") @RequestBody BookingDto bookingDto){
        Assert.notNull(bookingDto.getCreditCardNumber(), "Card number mustn't be null");
        return ResponseEntity.ok(bookingService.save(bookingDto));
    }

    /**
     * to get all items
     * @return List of items
     */
    @GetMapping
    @ApiMethod(description = "to get all Booking records")
    @ApiOperation(value = "Api method; to get all objects",notes = "this is a demo")
    public ResponseEntity<List<BookingDto>> getAll(){
        return ResponseEntity.ok(bookingService.getAll());
    }

    /**
     * To get page items by using params.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiMethod(description = "to get Booking page")
    @ApiOperation(value = "Api method; to get objects of the page ",notes = "this is a demo")
    public ResponseEntity<List<BookingDto>> getPage(
            @ApiPathParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiPathParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiPathParam(name = "sortBy") @RequestParam(defaultValue = "id") String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<BookingDto> resultPage = bookingService.getAll(pageable);
        return ResponseEntity.ok(resultPage.toList());
    }

    /**
     * To find by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    @ApiMethod(description = "searching, to find item by id")
    @ApiOperation(value = "Api method; to find a record using by id ",notes = "to find by id which is an identity")
    public ResponseEntity<BookingDto> findById(@ApiPathParam(name = "id") @RequestParam Long id) {
        BookingDto result = bookingService.findById(id);
        if (result !=null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     *  to book a flight
     * @param customerId
     * @param flightId
     * @param creditCardNumber
     * @param creditCardName
     * @param cardExpiryYear
     * @param cardExpiryMonth
     * @param cardType
     * @return
     */
    @RequestMapping(value = "/bookflight",method = RequestMethod.GET)
    @ApiMethod(description = "Booking Api method, to book a Flight by customer id and name flight id")
    @ApiOperation(value = "Api method; to find,search objects using  by customer id and name flight id ",notes = "this is a demo")
    public ResponseEntity<BookingDto> bookFlight(@ApiPathParam(name = "customerId") @RequestParam @NotNull Long customerId,
                                                 @ApiPathParam(name = "flightId") @RequestParam @NotNull Long  flightId,
                                                 @ApiPathParam(name = "creditCardNumber") @RequestParam @NotNull String creditCardNumber,
                                                 @ApiPathParam(name = "creditCardName") @RequestParam String creditCardName,
                                                 @ApiPathParam(name = "cardExpiryYear") @RequestParam @NotNull int cardExpiryYear,
                                                 @ApiPathParam(name = "cardExpiryMonth") @RequestParam @NotNull int cardExpiryMonth,
                                                 @ApiPathParam(name = "cardType") @RequestParam @NotNull CreditCardType cardType) {
        CustomerDto customerDto = customerService.findById(customerId);
        FlightDto flightDto = flightService.findById(flightId);
        String maskedCreditCardNumber = CardUtil.maskCardNumber(CardUtil.mask,creditCardNumber);
        if (customerDto !=null && flightDto !=null){
            BookingDto bookingDto = new BookingDto();
            bookingDto.setCreditCardExpiryMonth(cardExpiryMonth);
            bookingDto.setCreditCardExpiryYear(cardExpiryYear);
            bookingDto.setCreditCardName(creditCardName);
            bookingDto.setCreditCardNumber(maskedCreditCardNumber);
            bookingDto.setCustomer(customerDto);
            bookingDto.setFlight(flightDto);
            bookingDto.setBookingDate(new Date());
            bookingDto.setCreditCardType(cardType);
            bookingDto.setId(0L);
            // Set ticket price
            Double ticketPrice = flightService.getTicketPriceByFlightId(flightId);
            bookingDto.setTicketPrice(ticketPrice);

            bookingDto = bookingService.save(bookingDto);
            return ResponseEntity.ok(bookingDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * To cancel by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancel",method=RequestMethod.DELETE)
    @ApiMethod(description = "delete, to cancel item by id")
    @ApiOperation(value = "Api method; to delete a record using by id ",notes = "to delete by id which is an identity")
    public ResponseEntity<Void> deleteById(@ApiPathParam(name = "id") @RequestParam Long id) {
        try {
            bookingService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Throwable e) {
            return ResponseEntity.notFound().build();
        }
    }

}
