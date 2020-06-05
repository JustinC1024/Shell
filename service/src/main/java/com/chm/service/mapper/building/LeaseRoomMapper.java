package com.chm.service.mapper.building;

import com.chm.service.pojo.building.LeaseRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaseRoomMapper {

    /**
     * 增
     * @param leaseRoom
     * @return
     */
    public int doLeaseRoom(LeaseRoom leaseRoom);

    /**
     * 改
     * @param leaseRoom
     * @return
     */
    public int updateLeaseRoom(LeaseRoom leaseRoom);

    /**
     * 查（county/mode/apartment/LeaseRoomBasic.orientation/LeasePrice.rent/title/updateTs）
     * @param leaseRoom
     * @return
     */
    public List<LeaseRoom> getListLeaseRoom(LeaseRoom leaseRoom);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public LeaseRoom getLeaseRoomByPk(String pk);

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
    public int updateLeaseRoomStatus(LeaseRoom leaseRoom);
}
