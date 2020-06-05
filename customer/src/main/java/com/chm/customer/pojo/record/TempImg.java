package com.chm.customer.pojo.record;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempImg {

    private String pkTempImg;
    private String pkForeign;
    private byte[] img;
    private int dr;
    private String ts;

}
