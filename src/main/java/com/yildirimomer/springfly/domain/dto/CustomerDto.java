package com.yildirimomer.springfly.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */

@Data
@EqualsAndHashCode(of={"id"},callSuper = false)
@ApiModel(value = "CustomerDto model",description = "description about CustomerDto model",parent = BaseDto.class)
public class CustomerDto extends BaseDto{
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