package com.chm.service.service;

import com.chm.service.pojo.building.Village;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 小区
 */
public interface VillageService {

    /**
     * 增
     * @param village
     * @return
     */
    public int doVillage(Village village) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     */
    public int deleteVillage(String pk) throws Exception;

    /**
     * 改
     * @param village
     * @return
     */
    public int updateVillage(Village village) throws Exception;

    /**
     * 查（列表）
     * @param village
     * @param nowPage
     * @return
     */
    public List<Village> getListVillage(Village village, int nowPage);

    /**
     * 查（单个）
     * @param pk
     * @return
     */
    public Village getVillageByPk(String pk);

    /**
     * 页码
     * @param village
     * @return
     */
    public PageInfo<Village> getListVillagePage(Village village);

    /**
     * 查（title）
     * @param title
     * @return
     */
    public Village getVillageByTitle(String title);

}
