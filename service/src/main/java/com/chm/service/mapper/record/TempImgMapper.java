package com.chm.service.mapper.record;

import com.chm.service.pojo.record.TempImg;

import java.util.List;

public interface TempImgMapper {

    /**
     * 增
     * @param tempImg
     * @return
     */
    public int doTempImg(TempImg tempImg);

    /**
     * 删
     * @return
     */
    public int deleteTempImg();

    /**
     * 改
     * @param tempImg
     * @return
     */
    public int updateTempImg(TempImg tempImg);

    /**
     * 查（foreign）
     * @param pk
     * @return
     */
    public TempImg getTempImgByPk(String pk);

}
