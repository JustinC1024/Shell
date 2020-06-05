package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LeasePrice implements Serializable {

    private String pkLeasePrice;
    private String payment;
    private Integer rent;
    private Integer deposit;
    private Integer service;
    private String agency;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
