package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoomImg implements Serializable {

    private String pkRoomImg;
    private String title;
    private byte[] img;
    private Integer ind;
    private String pkRoom;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
