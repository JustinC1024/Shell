package com.chm.service.pojo.record;

import com.chm.service.pojo.building.LeaseRoom;
import com.chm.service.pojo.building.SellRoom;
import com.chm.service.pojo.human.Clerk;
import com.chm.service.pojo.human.Customer;
import com.chm.service.pojo.human.Support;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Bill implements Serializable {

    private String pkBill;
    private Support pkSupport;
    private LeaseRoom pkLeaseRoom;
    private SellRoom pkSellRoom;
    private Customer pkCustomer;
    private Clerk pkClerk;
    private Integer status;
    private String remake;
    private String result;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
