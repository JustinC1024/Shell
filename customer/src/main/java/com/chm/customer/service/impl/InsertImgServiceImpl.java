package com.chm.customer.service.impl;

import com.chm.customer.mapper.RoomImgMapper;
import com.chm.customer.mapper.SellRoomCompartmentMapper;
import com.chm.customer.pojo.building.RoomImg;
import com.chm.customer.pojo.building.SellRoomCompartment;
import com.chm.customer.service.InsertImgService;
import com.chm.customer.util.PkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InsertImgServiceImpl implements InsertImgService {

    @Autowired
    private RoomImgMapper roomImgMapper;
    @Autowired
    private SellRoomCompartmentMapper sellRoomCompartmentMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String doRoomImg(RoomImg roomImg) throws Exception {
        // 参数判断
        if (roomImg == null) {
            logger.warn("InsertImgService.doRoomImg[参数为空]");
            throw new Exception("参数为空");
        }
        String pk = pkBuilder.getPk(roomImg.toString());
        roomImg.setPkRoomImg(pk);
        roomImg.setTs(format.format(new Date().getTime()));
        // 插入判断
        if (roomImgMapper.doRoomImg(roomImg) == 0) {
            logger.warn("InsertImgService.doRoomImg[roomImgMapper.doRoomImg(roomImg)有误]");
            throw new Exception("插入图片失败");
        }
        logger.debug("InsertImgService.doRoomImg[执行完毕]");
        return pk;
    }

    @Override
    public String doSellRoomCompartment(SellRoomCompartment sellRoomCompartment) throws Exception {
        // 参数判断
        if (sellRoomCompartment == null) {
            logger.warn("InsertImgService.doSellRoomCompartment[参数为空]");
            throw new Exception("参数为空");
        }
        String pk = pkBuilder.getPk(sellRoomCompartment.toString());
        sellRoomCompartment.setPkSellRoomCompartment(pk);
        sellRoomCompartment.setTs(format.format(new Date().getTime()));
        // 插入判断
        if (sellRoomCompartmentMapper.doSellRoomCompartment(sellRoomCompartment) == 0) {
            logger.warn("InsertImgService.doSellRoomCompartment[sellRoomCompartmentMapper.doSellRoomCompartment(sellRoomCompartment)有误]");
            throw new Exception("插入图片失败");
        }
        logger.debug("InsertImgService.doSellRoomCompartment[执行完毕]");
        return pk;
    }
}
