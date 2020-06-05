package com.chm.service.pojo.human;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Jurisdiction implements Serializable {

    private String pkJurisdiction;
    private String name;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
