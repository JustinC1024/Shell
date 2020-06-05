package com.chm.service.service;

import com.chm.service.pojo.building.RoomImg;

public interface RoomImgService {

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public RoomImg getImgInRoomImgByPk(String pk);

    /**
     * 增
     * @param roomImg
     * @return
     * @throws Exception
     */
    public String doRoomImg(RoomImg roomImg) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteRoomImg(String pk) throws Exception;
}
