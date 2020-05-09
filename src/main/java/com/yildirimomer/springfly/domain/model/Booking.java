package com.yildirimomer.springfly.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Entity
@Table(name= "Booking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ApiModel(value = "Booking model", description = "description about Booking model",parent = BaseEntity.class)
public class Booking extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
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

    @ApiModelProperty(name = "customer",value = "customer",dataType = "Customer")
    @ManyToOne(targetEntity =Customer.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ApiModelProperty(name = "flight",value = "flight",dataType = "Flight")
    @ManyToOne(targetEntity =Flight.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @ApiModelProperty(name = "ticket",value = "ticket",dataType = "Ticket")
    @OneToOne(targetEntity =Ticket.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    @ApiModelProperty(name = "ticketPrice",value = "ticketPrice",dataType = "Double")
    private double ticketPrice;
}