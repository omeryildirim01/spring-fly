package com.yildirimomer.springfly.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Data
@EqualsAndHashCode(of={"id"},callSuper = false)
@ApiModel(value = "AirlineRouteDto model",description = "description about AirlineRouteDto model",parent = BaseDto.class)
public class AirlineRouteDto extends BaseDto{
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;
    @ApiModelProperty(name = "name",value = "name",dataType = "String")
    private String name;
    @ApiModelProperty(name = "code",value = "code",dataType = "String")
    private String code;
    @ApiModelProperty(name = "flightTime",value = "flightTime",dataType = "Integer")
    private int flightTime;
    @ApiModelProperty(name = "flightDistance",value = "flightDistance",dataType = "Integer")
    private int flightDistance;
    @ApiModelProperty(name = "departureAirport",value = "departureAirport",dataType = "AirportDto")
    private AirportDto departureAirport;
    @ApiModelProperty(name = "arrivalAirport",value = "arrivalAirport",dataType = "AirportDto")
    private AirportDto arrivalAirport;
}