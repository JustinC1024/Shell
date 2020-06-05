package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class LeaseRoom implements Serializable {

    private String pkLeaseRoom;
    @NotBlank(message = "leaseRoom.title")
    private String title;
    private String county;
    private String location;
    private String apartment;
    private Integer mode;
    private String publicTs;
    private String updateTs;
    private LeasePrice pkLeasePrice;
    private LeaseRoomBasic pkLeaseRoomBasic;
    private LeaseRoomMating pkLeaseRoomMating;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;
    private RoomImg img;
    private List<RoomImg> imgs;
    private List<String> deleteImg;
    private int status;
}
