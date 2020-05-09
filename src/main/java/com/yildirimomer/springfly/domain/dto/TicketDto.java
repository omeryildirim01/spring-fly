package com.yildirimomer.springfly.domain.dto;

import com.yildirimomer.springfly.domain.model.Booking;
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
@ApiModel(value = "TicketDto model",description = "description about TicketDto model",parent = BaseDto.class)
public class TicketDto extends BaseDto{
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;

    @ApiModelProperty(name = "ticketNumber",value = "ticketNumber",dataType = "String")
    private String ticketNumber;

    @ApiModelProperty(name = "gate",value = "gate",dataType = "String")
    private String gate;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "checkinDate",value = "checkinDate",dataType = "Date")
    private java.util.Date checkinDate;

    @ApiModelProperty(name = "booking",value = "booking",dataType = "BookingDto")
    private BookingDto booking;

}