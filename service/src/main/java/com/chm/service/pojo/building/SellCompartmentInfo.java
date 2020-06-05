package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellCompartmentInfo implements Serializable {

    private String pkSellCompartmentInfo;
    private float area;
    private String direction;
    private String windows;
    private SellCompartmentType pkSellCompartmentType;
    private String pkSellRoomCompartment;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
