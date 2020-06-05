package com.chm.service.mapper.building;

import com.chm.service.pojo.building.LeaseRoomMating;
import org.apache.ibatis.annotations.Param;

public interface LeaseRoomMatingMapper {

    /**
     * 增
     * @param leaseRoomMating
     * @return
     */
    public int doLeaseRoomMating(LeaseRoomMating leaseRoomMating);

    /**
     * 改
     * @param leaseRoomMating
     * @return
     */
    public int updateLeaseRoomMating(LeaseRoomMating leaseRoomMating);

}
