package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LeaseRoomMating implements Serializable  {

    private String pkLeaseRoomMating;
    private Integer television;
    private Integer fridge;
    private Integer washing;
    private Integer air;
    private Integer heater;
    private Integer bed;
    private Integer heating;
    private Integer broadband;
    private Integer wardrobe;
    private Integer gas;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
