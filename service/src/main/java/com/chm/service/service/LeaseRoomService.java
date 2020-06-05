package com.chm.service.service;

import com.chm.service.pojo.building.LeaseRoom;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 租房
 */
public interface LeaseRoomService {

    /**
     * 增
     * @param leaseRoom
     * @return
     * @throws Exception
     */
    public String doLeaseRoom(LeaseRoom leaseRoom) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteLeaseRoom(String pk) throws Exception;

    /**
     * 改
     * @param leaseRoom
     * @return
     * @throws Exception
     */
    public int updateLeaseRoom(LeaseRoom leaseRoom) throws Exception;

    /**
     * 查（列表）
     * @param leaseRoom
     * @param nowPage
     * @return
     */
    public List<LeaseRoom> getListLeaseRoom(LeaseRoom leaseRoom, int nowPage);

    /**
     * 查（单个）
     * @return
     */
    public LeaseRoom getLeaseRoomByPk(String pk);

    /**
     * 页码
     * @param leaseRoom
     * @return
     */
    public PageInfo<LeaseRoom> getListLeaseRoomPage(LeaseRoom leaseRoom);


    /**
     * 查（title）
     * @param title
     * @return
     */
    public LeaseRoom getLeaseRoomByTitle(String title);

    /**
     * 改status
     * @param leaseRoom
     * @return
     */
    public int updateLeaseRoomStatus(LeaseRoom leaseRoom) throws Exception;

}
