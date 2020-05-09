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
@Table(name= "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ApiModel(value = "Customer model", description = "description about Customer model",parent = BaseEntity.class)
public class Customer extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;

    @ApiModelProperty(name = "name",value = "name",dataType = "String")
    private String name;

    @ApiModelProperty(name = "telephone",value = "telephone",dataType = "String")
    private String telephone;

    @ApiModelProperty(name = "mail",value = "mail",dataType = "String")
    private String mail;

    @ApiModelProperty(name = "address",value = "address",dataType = "String")
    private String address;

}