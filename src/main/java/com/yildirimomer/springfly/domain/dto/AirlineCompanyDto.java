package com.yildirimomer.springfly.domain.dto;

import com.yildirimomer.springfly.domain.model.BaseEntity;
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
@ApiModel(value = "AirlineCompanyDto model",description = "description about AirlineCompanyDto model",parent = BaseDto.class)
public class AirlineCompanyDto extends BaseDto{
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
