package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellRoomBasic;
import org.apache.ibatis.annotations.Param;

public interface SellRoomBasicMapper {

    /**
     * 增
     * @param sellRoomBasic
     * @return
     */
    public int doSellRoomBasic(SellRoomBasic sellRoomBasic);

    /**
     * 改
     * @param sellRoomBasic
     * @return
     */
    public int updateSellRoomBasic(SellRoomBasic sellRoomBasic);

}
