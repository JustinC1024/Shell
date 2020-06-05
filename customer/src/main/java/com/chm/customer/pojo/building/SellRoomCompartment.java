package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SellRoomCompartment implements Serializable {

    private String pkSellRoomCompartment;
    private byte[] img;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;
    private List<SellCompartmentInfo> compartments;
    private List<String> deleteCompartment;

}
