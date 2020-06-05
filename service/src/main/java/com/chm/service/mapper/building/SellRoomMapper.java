package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellRoom;

import java.util.List;

public interface SellRoomMapper {

    /**
     * 增
     * @param sellRoom
     * @return
     */
    public int doSellRoom(SellRoom sellRoom);

    /**
     * 改
     * @param sellRoom
     * @return
     */
    public int updateSellRoom(SellRoom sellRoom);

    /**
     * 查（county/SellPrice.totalPrice/SellRoomBasic.apartment/SellRoomBasic.builtUpArea/updateTs/title）
     * @param sellRoom
     * @return
     */
    public List<SellRoom> getListSellRoom(SellRoom sellRoom);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public SellRoom getSellRoomByPk(String pk);

    /**
     * 改Compartment
     * @param sellRoom
     * @return
     */
    public int updateCompartment(SellRoom sellRoom);

    /**
     * 查（title）
     * @param title
     * @return
     */
    public SellRoom getSellRoomByTitle(String title);

    /**
     * 改status
     * @param sellRoom
     * @return
     */
    public int updateSellRoomStatus(SellRoom sellRoom);
}
