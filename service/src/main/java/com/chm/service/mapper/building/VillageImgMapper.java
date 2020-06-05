package com.chm.service.mapper.building;

import com.chm.service.pojo.building.VillageImg;

import java.util.List;

public interface VillageImgMapper {

    /**
     * 增
     * @param villageImg
     * @return
     */
    public int doVillageImg(VillageImg villageImg);

    /**
     * 批量增
     * @param villageImg
     * @return
     */
    public int doListVillageImg(List<VillageImg> villageImg);

    /**
     * 改
     * @param villageImg
     * @return
     */
    public int updateVillageImg(VillageImg villageImg);

    /**
     * 改（pkVillage）
     * @param pk
     * @return
     */
    public int updateVillageImgByVillage(String pk);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public VillageImg getVillageImgByPk(String pk);

}
