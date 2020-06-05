package com.chm.service.pojo.human;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Support implements Serializable {

    private String pkSupport;
    private String title;
    private String instruction;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
