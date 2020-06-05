package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellRoomCharacteristic;

public interface SellRoomCharacteristicMapper {

    /**
     * 增
     * @param sellRoomCharacteristic
     * @return
     */
    public int doSellRoomCharacteristic(SellRoomCharacteristic sellRoomCharacteristic);

    /**
     * 改
     * @param sellRoomCharacteristic
     * @return
     */
    public int updateSellRoomCharacteristic(SellRoomCharacteristic sellRoomCharacteristic);

}
