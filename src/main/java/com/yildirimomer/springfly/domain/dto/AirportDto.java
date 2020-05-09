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
@ApiModel(value = "AirportDto model",description = "description about AirportDto model",parent = BaseDto.class)
public class AirportDto extends BaseDto {
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;
    @ApiModelProperty(name = "name",value = "name",dataType = "String")
    private String name;
    @ApiModelProperty(name = "code",value = "code",dataType = "String")
    private String code;
    @ApiModelProperty(name = "country",value = "country",dataType = "String")
    private String country;
    @ApiModelProperty(name = "city",value = "city",dataType = "String")
    private String city;
}
