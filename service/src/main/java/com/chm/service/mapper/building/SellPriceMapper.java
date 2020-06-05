package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellPrice;
import org.apache.ibatis.annotations.Param;

public interface SellPriceMapper {

    /**
     * 增
     * @param sellPrice
     * @return
     */
    public int doSellPrice(SellPrice sellPrice);

    /**
     * 改
     * @param sellPrice
     * @return
     */
    public int updateSellPrice(SellPrice sellPrice);

}
