package com.chm.customer.service;

import com.chm.customer.pojo.building.RoomImg;
import com.chm.customer.pojo.building.SellRoomCompartment;

public interface InsertImgService {

    /**
     * 增roomImg
     * @param roomImg
     * @return
     * @throws Exception
     */
    public String doRoomImg(RoomImg roomImg) throws Exception;

    /**
     * 增SellRoomCompartment
     * @param sellRoomCompartment
     * @return
     * @throws Exception
     */
    public String doSellRoomCompartment(SellRoomCompartment sellRoomCompartment) throws Exception;

}
