package com.chm.service.mapper.building;

import com.chm.service.pojo.building.LeasePrice;
import org.apache.ibatis.annotations.Param;

public interface LeasePriceMapper {

    /**
     * 增
     * @param leasePrice
     * @return
     */
    public int doLeasePrice(LeasePrice leasePrice);

    /**
     * 改
     * @param leasePrice
     * @return
     */
    public int updateLeasePrice(LeasePrice leasePrice);

}
