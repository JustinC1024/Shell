package com.chm.customer.mapper;

import com.chm.customer.pojo.building.SellRoomCompartment;
import org.apache.ibatis.annotations.Insert;

public interface SellRoomCompartmentMapper {

    @Insert(value = " INSERT  INTO db_sell_room_compartment (pk_sell_room_compartment, img, ts, dr) VALUES " +
            "(#{pkSellRoomCompartment}, #{img}, #{ts}, 0)")
    public int doSellRoomCompartment(SellRoomCompartment sellRoomCompartment);

}
