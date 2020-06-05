package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Village implements Serializable {

    private String pkVillage;
    private String title;
    private String introduction;
    private String county;
    private String location;
    private Integer consult;
    private String buildingType;
    private String property;
    private String company;
    private String developer;
    private Integer buildingNum;
    private Integer roomNum;
    private String updateTs;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;
    private VillageImg img;
    private List<VillageImg> imgs;
    private List<String> deleteImg;
}
