package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LeaseRoomBasic implements Serializable {

    private String pkLeaseRoomBasic;
    private Integer area;
    private String orientation;
    private String maintain;
    private String checkIn;
    private String floor;
    private Integer floorNum;
    private String ladder;
    private String parking;
    private String water;
    private String electricity;
    private String gas;
    private String term;
    private String heating;
    private String inspection;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
