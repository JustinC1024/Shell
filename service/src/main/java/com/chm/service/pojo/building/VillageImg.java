package com.chm.service.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VillageImg implements Serializable {

    private String pkVillageImg;
    private byte[] img;
    private String pkVillage;
    private Integer ind;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
