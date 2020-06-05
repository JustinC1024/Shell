package com.chm.customer.mapper;

import com.chm.customer.pojo.building.RoomImg;
import org.apache.ibatis.annotations.Insert;

public interface RoomImgMapper {

    @Insert(value = "INSERT INTO db_room_img (pk_room_img, title, img, pk_room, ts, dr) VALUES " +
            "(#{pkRoomImg}, #{title}, #{img}, #{pkRoom}, #{ts}, 1)")
    int doRoomImg(RoomImg roomImg);

}
