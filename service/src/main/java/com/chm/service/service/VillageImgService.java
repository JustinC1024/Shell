package com.chm.service.service;

import com.chm.service.pojo.building.VillageImg;

import java.util.List;

public interface VillageImgService {

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public VillageImg getImgVillageImgByPk(String pk);

    /**
     * 增
     * @param villageImg
     * @return
     */
    public String doVillageImg(VillageImg villageImg) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteVillageImg(String pk) throws Exception;
}
