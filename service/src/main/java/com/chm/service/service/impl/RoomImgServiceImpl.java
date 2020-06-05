package com.chm.service.service.impl;

import com.chm.service.mapper.building.RoomImgMapper;
import com.chm.service.pojo.building.RoomImg;
import com.chm.service.service.RoomImgService;
import com.chm.service.util.PkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@CacheConfig(cacheNames = "roomImg")
@Service
public class RoomImgServiceImpl implements RoomImgService {

    @Autowired
    private RoomImgMapper roomImgMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    @Cacheable(key = "#pk")
    public RoomImg getImgInRoomImgByPk(String pk) {
        return roomImgMapper.getImgInRoomImgByPk(pk);
    }

    @Override
    public String doRoomImg(RoomImg roomImg) throws Exception {
        // 参数判断
        if (roomImg == null) {
            logger.warn("RoomImgService.doRoomImg[参数为空]");
            throw new Exception("参数为空");
        }
        String pk = pkBuilder.getPk(roomImg.toString());
        roomImg.setPkRoomImg(pk);
        roomImg.setTs(format.format(new Date().getTime()));
        // 插入判断
        if (roomImgMapper.doRoomImg(roomImg) == 0) {
            logger.warn("RoomImgService.doRoomImg[roomImgMapper.doRoomImg(roomImg)有误]");
            throw new Exception("插入图片失败");
        }
        logger.debug("RoomImgService.doRoomImg[执行完毕]");
        return pk;
    }

    @Override
    @CacheEvict(key = "#pk")
    public int deleteRoomImg(String pk) throws Exception {
        if(pk == null || "".equals(pk)){
            logger.warn("RoomImgService.deleteRoomImg[参数为空]");
            throw new Exception("参数为空");
        }
        RoomImg roomImg = new RoomImg();
        roomImg.setPkRoomImg(pk);
        roomImg.setTs(format.format(new Date().getTime()));
        roomImg.setDr(1);
        if(roomImgMapper.updateRoomImg(roomImg) == 0){
            logger.warn("RoomImgService.deleteRoomImg[roomImgMapper.updateRoomImg(roomImg)有误]");
            throw new Exception("更改失败");
        }
        return 1;
    }
}
