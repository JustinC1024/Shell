package com.chm.service.service;

import com.chm.service.pojo.building.SellCompartmentType;
import com.chm.service.pojo.building.SellRoom;
import com.chm.service.pojo.building.SellRoomCompartment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 二手房
 */
public interface SellRoomService {

    /**
     * 增
     * @param sellRoom
     * @return
     */
    public String doSellRoom(SellRoom sellRoom) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     */
    public int deleteSellRoom(String pk) throws Exception;

    /**
     * 改
     * @param sellRoom
     * @return
     */
    public int updateSellRoom(SellRoom sellRoom) throws Exception;

    /**
     * 查（列表）
     * @param sellRoom
     * @param nowPage
     * @return
     */
    public List<SellRoom> getListSellRoom(SellRoom sellRoom, int nowPage);

    /**
     * 查（单个）
     * @param pk
     * @return
     */
    public SellRoom getSellRoomByPk(String pk);

    /**
     * 查sell_compartment_type
     * @return
     */
    public List<SellCompartmentType> getAllSellCompartmentType();

    /**
     * 页码
     * @param sellRoom
     * @return
     */
    public PageInfo<SellRoom> getListSellRoomPage(SellRoom sellRoom);

    /**
     * 查SellRoomCompartment（pk）
     * @param pk
     * @return
     */
    public SellRoomCompartment getImgSellRoomCompartmentByPk(String pk);

    /**
     * 增SellRoomCompartment
     * @param sellRoomCompartment
     * @return
     * @throws Exception
     */
    public String doSellRoomCompartment(SellRoomCompartment sellRoomCompartment) throws Exception;

    /**
     * sellRoom增sellRoomCompartment
     * @param sellRoom
     * @return
     * @throws Exception
     */
    public int doCompartmentInfo(SellRoom sellRoom) throws Exception;

    /**
     * 改sellRoomCompartment
     * @param sellRoom
     * @return
     * @throws Exception
     */
    public int updateCompartmentInfo(SellRoom sellRoom) throws Exception;

    /**
     * 查（title）
     * @param title
     * @return
     */
    public SellRoom getSellRoomByTitle(String title);

    /**
     * 删SellRoomCompartmentInfo
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteSellRoomCompartmentInfo(String pk) throws Exception;

    /**
     * 改status
     * @param sellRoom
     * @return
     */
    public int updateSellRoomStatus(SellRoom sellRoom) throws Exception;
}
