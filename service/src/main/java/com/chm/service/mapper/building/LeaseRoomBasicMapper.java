package com.chm.service.mapper.building;

import com.chm.service.pojo.building.LeaseRoomBasic;
import org.apache.ibatis.annotations.Param;

public interface LeaseRoomBasicMapper {

    /**
     * 增
     * @param leaseRoomBasic
     * @return
     */
    public int doLeaseRoomBasic(LeaseRoomBasic leaseRoomBasic);

    /**
     * 改
     * @param leaseRoomBasic
     * @return
     */
    public int updateLeaseRoomBasic(LeaseRoomBasic leaseRoomBasic);

}
