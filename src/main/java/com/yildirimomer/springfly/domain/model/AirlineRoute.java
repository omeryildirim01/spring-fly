package com.yildirimomer.springfly.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Entity
@Table(name= "AirlineRoute")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ApiModel(value = "AirlineRoute model",description = "description about Airline Route model",parent = BaseEntity.class)
public class AirlineRoute extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @ApiModelProperty(name = "departureAirport",value = "departureAirport",dataType = "Airport")
    @OneToOne(targetEntity =Airport.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirport;

    @ApiModelProperty(name = "arrivalAirport",value = "arrivalAirport",dataType = "Airport")
    @OneToOne(targetEntity =Airport.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirport;

}