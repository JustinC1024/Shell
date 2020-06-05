package com.chm.customer.pojo.human;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Clerk implements Serializable {

    private String pkClerk;
    private String name;
    private byte[] img;
    private String introduction;
    private Integer phone;
    private String account;
    private String password;
    private Jurisdiction pkJurisdiction;
    private String parent;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
