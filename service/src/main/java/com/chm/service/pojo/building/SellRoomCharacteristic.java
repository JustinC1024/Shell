package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellRoomCharacteristic implements Serializable {

    private String pkSellRoomCharacteristic;
    private String core;
    private String apartment;
    private String village;
    private String crowd;
    private String situation;
    private String traffic;
    private String surround;
    private String taxation;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
