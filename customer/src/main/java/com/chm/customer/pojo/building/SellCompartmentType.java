package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellCompartmentType implements Serializable {

    private String pkSellCompartmentType;
    private String title;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
