package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellRoomCompartment;

public interface SellRoomCompartmentMapper {

    /**
     * 增
     * @param sellRoomCompartment
     * @return
     */
    public int doSellRoomCompartment(SellRoomCompartment sellRoomCompartment);

    /**
     * 改
     * @param sellRoomCompartment
     * @return
     */
    public int updateSellRoomCompartment(SellRoomCompartment sellRoomCompartment);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public SellRoomCompartment getImgSellRoomCompartmentByPk(String pk);
}
