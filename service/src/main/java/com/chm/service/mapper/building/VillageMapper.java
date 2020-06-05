package com.chm.service.mapper.building;

import com.chm.service.pojo.building.Village;

import java.util.List;

public interface VillageMapper {

    /**
     * 增
     * @param village
     * @return
     */
    public int doVillage(Village village);

    /**
     * 改
     * @param village
     * @return
     */
    public int updateVillage(Village village);

    /**
     * 查（title/county/consult）
     * @param village
     * @return
     */
    public List<Village> getListVillage(Village village);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public Village getVillageByPk(String pk);

    /**
     * 查（title）
     * @param title
     * @return
     */
    public Village getVillageByTitle(String title);

}
