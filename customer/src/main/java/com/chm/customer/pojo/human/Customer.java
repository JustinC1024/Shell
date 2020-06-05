package com.chm.customer.pojo.human;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Customer implements Serializable {

    private String pkCustomer;
    private String account;
    private String password;
    private byte[] portrait;
    private String name;
    private String identity;
    private String city;
    private int gender;
    private String birth;
    private String mail;
    private String phone;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
