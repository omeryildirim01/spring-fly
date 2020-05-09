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
@Table(name= "Airport")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"},callSuper = false)
@ApiModel(value = "Airport model",description = "description about Airport model",parent = BaseEntity.class)
public class Airport extends BaseEntity  implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "id",value = "id",dataType = "Long")
    private Long id;
    @NonNull
    @ApiModelProperty(name = "name",value = "name",dataType = "String")
    private String name;
    @ApiModelProperty(name = "code",value = "code",dataType = "String")
    private String code;
    @ApiModelProperty(name = "country",value = "country",dataType = "String")
    private String country;
    @ApiModelProperty(name = "city",value = "city",dataType = "String")
    private String city;
}
