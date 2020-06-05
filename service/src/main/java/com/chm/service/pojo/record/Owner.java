package com.chm.service.pojo.record;

import com.chm.service.pojo.building.LeaseRoom;
import com.chm.service.pojo.building.SellRoom;
import com.chm.service.pojo.human.Clerk;
import com.chm.service.pojo.human.Customer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Owner implements Serializable {

    private String pkOwner;
    private Customer pkCustomer;
    private Clerk pkClerk;
    private LeaseRoom pkLeaseRoom;
    private SellRoom pkSellRoom;
    private Integer status;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
