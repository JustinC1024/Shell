package com.chm.service.mapper.building;

import com.chm.service.pojo.building.SellCompartmentType;

import java.util.List;

public interface SellCompartmentTypeMapper {

    /**
     * 增
     * @param sellCompartmentType
     * @return
     */
    public int doSellCompartmentType(SellCompartmentType sellCompartmentType);

    /**
     * 查（title）
     * @return
     */
    public List<SellCompartmentType> getAllSellCompartmentType();

}
