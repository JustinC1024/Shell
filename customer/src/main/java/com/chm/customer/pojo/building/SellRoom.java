package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SellRoom implements Serializable {

    private String pkSellRoom;
    private String title;
    private String county;
    private String location;
    private String publicTs;
    private String updateTs;
    private SellPrice pkSellPrice;
    private SellRoomTransaction pkSellRoomTransaction;
    private SellRoomBasic pkSellRoomBasic;
    private SellRoomCharacteristic pkSellRoomCharacteristic;
    private SellRoomCompartment pkSellRoomCompartment;
    private Village pkVillage;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;
    private RoomImg img;
    private List<RoomImg> imgs;
    private int status;
    private List<String> deleteImg;

}
