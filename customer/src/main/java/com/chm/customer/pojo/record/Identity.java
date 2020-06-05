package com.chm.customer.pojo.record;

import com.chm.customer.pojo.human.Clerk;
import com.chm.customer.pojo.human.Customer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Identity implements Serializable {

    private String pkIdentity;
    private Customer pkCustomer;
    private Clerk pkClerk;
    private Integer status;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
