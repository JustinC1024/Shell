package com.chm.service.mapper.building;

import com.chm.service.pojo.building.RoomImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomImgMapper {

    /**
     * 增
     * @param roomImg
     * @return
     */
    public int doRoomImg(RoomImg roomImg);

    /**
     * 批量增
     * @param roomImg
     * @return
     */
    public int doListRoomImg(List<RoomImg> roomImg);

    /**
     * 改
     * @param roomImg
     * @return
     */
    public int updateRoomImg(RoomImg roomImg);

    /**
     * 批量改
     * @param pk
     * @return
     */
    public int updateListRoomImg(List<String> pk);

    /**
     * 查pk（pk_room）
     * @param pk
     * @return
     */
    public List<String> getRoomImgByRoom(String pk);

    /**
     * 查img（pk）
     * @param pk
     * @return
     */
    public RoomImg getImgInRoomImgByPk(String pk);
}
