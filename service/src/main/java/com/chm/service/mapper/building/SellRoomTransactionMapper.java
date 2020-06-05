package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellRoomTransaction;

public interface SellRoomTransactionMapper {

    /**
     * 增
     * @param sellRoomTransaction
     * @return
     */
    public int doSellRoomTransaction(SellRoomTransaction sellRoomTransaction);

    /**
     * 改
     * @param sellRoomTransaction
     * @return
     */
    public int updateSellRoomTransaction(SellRoomTransaction sellRoomTransaction);

}
