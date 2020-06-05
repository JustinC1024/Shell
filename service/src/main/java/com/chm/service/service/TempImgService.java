package com.chm.service.service;

import com.chm.service.pojo.record.TempImg;

import java.util.List;

public interface TempImgService {

    /**
     * 增
     * @param tempImg
     * @return
     */
    public String doTempImg(TempImg tempImg) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     */
    public int deleteTempImg(String pk) throws Exception;

    /**
     * 查（foreign）
     * @param pk
     * @return
     */
    public TempImg getTempImgByPk(String pk);

}
