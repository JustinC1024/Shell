package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LeaseRoomMating implements Serializable  {

    private String pkLeaseRoomMating;
    private int television;
    private int fridge;
    private int washing;
    private int air;
    private int heater;
    private int bed;
    private int heating;
    private int broadband;
    private int wardrobe;
    private int gas;
    private String ts;
    private int dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
