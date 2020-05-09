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
@Table(name= "AirlineCompany")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ApiModel(value = "AirlineCompany model",description = "description about airline company model",parent = BaseEntity.class)
public class AirlineCompany extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;
    @ApiModelProperty(name = "name",value = "name",dataType = "String")
    private String name;
    @ApiModelProperty(name = "address",value = "address",dataType = "String")
    private String address;
    @ApiModelProperty(name = "telephone",value = "telephone",dataType = "String")
    private String telephone;
    @ApiModelProperty(name = "mail",value = "mail",dataType = "String")
    private String mail;
}