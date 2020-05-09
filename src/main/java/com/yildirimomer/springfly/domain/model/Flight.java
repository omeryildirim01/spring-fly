package com.yildirimomer.springfly.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Entity
@Table(name= "Flight")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ApiModel(value = "Flight model", description = "description about Flight model",parent = BaseEntity.class)
public class Flight extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "departureDate",value = "departureDate",dataType = "Date")
    private java.util.Date departureDate;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "arrivalDate",value = "arrivalDate",dataType = "Date")
    private java.util.Date arrivalDate;

    @ApiModelProperty(name = "airlineCompany",value = "airlineCompany",dataType = "AirlineCompany")
    @OneToOne(targetEntity =AirlineCompany.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_company_id", referencedColumnName = "id")
    private AirlineCompany airlineCompany;

    @ApiModelProperty(name = "airlineRoute",value = "airlineRoute",dataType = "AirlineRoute")
    @OneToOne(targetEntity =AirlineRoute.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_route_id", referencedColumnName = "id")
    private AirlineRoute airlineRoute;


}