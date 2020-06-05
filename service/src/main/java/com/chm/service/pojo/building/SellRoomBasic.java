package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellRoomBasic implements Serializable {

    private String pkSellRoomBasic;
    private String apartment;
    private String floor;
    private Integer floorNum;
    private String builtUpArea;
    private String area;
    private String type;
    private String orientation;
    private String building;
    private String decoration;
    private String structure;
    private String ladder;
    private Integer propertyYears;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
