package com.chm.customer.pojo.building;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellRoomTransaction implements Serializable {

    private String pkSellRoomTransaction;
    private String listingTs;
    private String ownership;
    private String lastTs;
    private String purpose;
    private String years;
    private String property;
    private String mortgage;
    private String permit;
    private String ts;
    private Integer dr;
    private String spare1;
    private String spare2;
    private String spare3;

}
