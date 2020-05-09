package com.yildirimomer.springfly.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */

@Entity
@Table(name= "Ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ApiModel(value = "Ticket model", description = "description about Ticket model",parent = BaseEntity.class)
public class Ticket extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @ApiModelProperty(name = "booking",value = "booking",dataType = "Booking")
    @OneToOne(targetEntity =Booking.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

}