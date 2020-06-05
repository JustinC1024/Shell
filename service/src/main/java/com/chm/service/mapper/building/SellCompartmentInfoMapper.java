package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellCompartmentInfo;
import org.apache.ibatis.annotations.Param;

public interface SellCompartmentInfoMapper {

    /**
     * 增
     * @param sellCompartmentInfo
     * @return
     */
    public int doSellCompartmentInfo(SellCompartmentInfo sellCompartmentInfo);

    /**
     * 改
     * @param sellCompartmentInfo
     * @return
     */
    public int updateSellCompartmentInfo(SellCompartmentInfo sellCompartmentInfo);

    /**
     * 改（pk_sell_room_compartment）
     * @param pk
     * @return
     */
    public int updateSellCompartmentInfoByCompartment(String pk);
}
