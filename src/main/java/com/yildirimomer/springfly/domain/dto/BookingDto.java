package com.yildirimomer.springfly.domain.dto;

import com.yildirimomer.springfly.domain.model.CreditCardType;
import com.yildirimomer.springfly.domain.model.Customer;
import com.yildirimomer.springfly.domain.model.Flight;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Data
@EqualsAndHashCode(of={"id"},callSuper = false)
@ApiModel(value = "BookingDto model",description = "description about BookingDto model",parent = BaseDto.class)
public class BookingDto extends BaseDto{
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;

    @ApiModelProperty(name = "bookingDate",value = "bookingDate",dataType = "Date")
    private java.util.Date bookingDate;

    @ApiModelProperty(name = "creditCardNumber",value = "creditCardNumber",dataType = "String")
    private String creditCardNumber;

    @ApiModelProperty(name = "creditCardType",value = "VISA,MASTERCARD,MAESTRO",dataType = "enum")
    @Enumerated
    private CreditCardType creditCardType;

    @ApiModelProperty(name = "creditCardName",value = "creditCardName",dataType = "String")
    private String creditCardName;

    @ApiModelProperty(name = "creditCardExpiryMonth",value = "creditCardExpiryMonth",dataType = "Integer")
    private int creditCardExpiryMonth;

    @ApiModelProperty(name = "creditCardExpiryYear",value = "creditCardExpiryYear",dataType = "Integer")
    private int creditCardExpiryYear;

    @ApiModelProperty(name = "customer",value = "customer",dataType = "CustomerDto")
    private CustomerDto customer;

    @ApiModelProperty(name = "flight",value = "flight",dataType = "FlightDto")
    private FlightDto flight;

    @ApiModelProperty(name = "ticket",value = "ticket",dataType = "TicketDto")
    private TicketDto ticket;

    @ApiModelProperty(name = "ticketPrice",value = "ticketPrice",dataType = "Double")
    private double ticketPrice;
}