package com.yildirimomer.springfly.domain.dto;

import com.yildirimomer.springfly.domain.model.AirlineCompany;
import com.yildirimomer.springfly.domain.model.AirlineRoute;
import com.yildirimomer.springfly.domain.model.Booking;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Data
@EqualsAndHashCode(of={"id"},callSuper = false)
@ApiModel(value = "FlightDto model",description = "description about FlightDto model",parent = BaseDto.class)
public class FlightDto extends BaseDto{
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;

    @ApiModelProperty(name = "name",value = "name",dataType = "String")
    private String name;

    @ApiModelProperty(name = "code",value = "code",dataType = "String")
    private String code;

    @ApiModelProperty(name = "quota",value = "quota",dataType = "Integer")
    private int quota;

    @ApiModelProperty(name = "price",value = "price",dataType = "Double")
    private double price;

    @ApiModelProperty(name = "departureDate",value = "departureDate",dataType = "Date")
    private java.util.Date departureDate;

    @ApiModelProperty(name = "arrivalDate",value = "arrivalDate",dataType = "Date")
    private java.util.Date arrivalDate;

    @ApiModelProperty(name = "airlineCompany",value = "airlineCompany",dataType = "AirlineCompanyDto")
    private AirlineCompanyDto airlineCompany;

    @ApiModelProperty(name = "airlineRoute",value = "airlineRoute",dataType = "AirlineRouteDto")
    private AirlineRouteDto airlineRoute;

}