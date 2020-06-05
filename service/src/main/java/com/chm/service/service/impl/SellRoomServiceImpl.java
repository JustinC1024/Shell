package com.chm.service.service.impl;

import com.chm.service.mapper.building.*;
import com.chm.service.pojo.building.*;
import com.chm.service.service.SellRoomService;
import com.chm.service.util.PkBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CacheConfig(cacheNames = "sell")
@Service
public class SellRoomServiceImpl implements SellRoomService {

    @Autowired
    private SellCompartmentInfoMapper sellCompartmentInfoMapper;
    @Autowired
    private SellCompartmentTypeMapper sellCompartmentTypeMapper;
    @Autowired
    private SellPriceMapper sellPriceMapper;
    @Autowired
    private SellRoomBasicMapper sellRoomBasicMapper;
    @Autowired
    private SellRoomCharacteristicMapper sellRoomCharacteristicMapper;
    @Autowired
    private SellRoomCompartmentMapper sellRoomCompartmentMapper;
    @Autowired
    private SellRoomMapper sellRoomMapper;
    @Autowired
    private SellRoomTransactionMapper sellRoomTransactionMapper;
    @Autowired
    private RoomImgMapper roomImgMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doSellRoom(SellRoom sellRoom) throws Exception {
        // 传入值为空判断
        if (sellRoom == null) {
            logger.warn("SellRoomService.doSellRoom传入值为空");
            throw new Exception("数据为空");
        }
        SellPrice sellPrice = sellRoom.getPkSellPrice();
        SellRoomBasic sellRoomBasic = sellRoom.getPkSellRoomBasic();
        SellRoomCharacteristic sellRoomCharacteristic = sellRoom.getPkSellRoomCharacteristic();
        SellRoomTransaction sellRoomTransaction = sellRoom.getPkSellRoomTransaction();
        RoomImg img = sellRoom.getImg();
        List<RoomImg> imgs = sellRoom.getImgs();
        // 关联对象为空判断
        if (sellPrice == null && sellRoomBasic == null && sellRoomTransaction == null) {
            logger.warn("SellRoomService.doSellRoom关联对象为空");
            throw new Exception("数据为空");
        }
        // 配置sell_price
        String pkSellPrice = pkBuilder.getPk(sellPrice.toString());
        sellPrice.setPkSellPrice(pkSellPrice);
        sellPrice.setTs(format.format(new Date().getTime()));
        // 插入sell_price
        if (sellPriceMapper.doSellPrice(sellPrice) == 0) {
            logger.warn("SellRoomService.doSellRoom[sellPriceMapper.doSellPrice(sellPrice)]有误");
            throw new Exception("价格插入失败");
        }
        sellRoom.getPkSellPrice().setPkSellPrice(pkSellPrice);
        // 配置sell_room_basic
        String pkSellRoomBasic = pkBuilder.getPk(sellRoomBasic.toString());
        sellRoomBasic.setPkSellRoomBasic(pkSellRoomBasic);
        sellRoomBasic.setTs(format.format(new Date().getTime()));
        // 插入sell_room_basic
        if (sellRoomBasicMapper.doSellRoomBasic(sellRoomBasic) == 0) {
            logger.warn("SellRoomService.doSellRoom[sellRoomBasicMapper.doSellRoomBasic" +
                    "(sellRoomBasic)]有误");
            throw new Exception("基本信息插入失败");
        }
        sellRoom.getPkSellRoomBasic().setPkSellRoomBasic(pkSellRoomBasic);
        // 配置sell_room_characteristic
        String pkSellRoomCharacteristic = pkBuilder.getPk(sellRoomCharacteristic.toString());
        sellRoomCharacteristic.setPkSellRoomCharacteristic(pkSellRoomCharacteristic);
        sellRoomCharacteristic.setTs(format.format(new Date().getTime()));
        // 插入sell_room_characteristic
        if (sellRoomCharacteristicMapper.doSellRoomCharacteristic(sellRoomCharacteristic) == 0) {
            logger.warn("SellRoomService.doSellRoom[sellRoomCharacteristicMapper." +
                    "doSellRoomCharacteristic(sellRoomCharacteristic)]有误");
            throw new Exception("特色插入失败");
        }
        sellRoom.getPkSellRoomCharacteristic().setPkSellRoomCharacteristic(pkSellRoomCharacteristic);
        // 配置sell_room_transaction
        String pkSellRoomTransaction = pkBuilder.getPk(sellRoomTransaction.toString());
        sellRoomTransaction.setPkSellRoomTransaction(pkSellRoomTransaction);
        sellRoomTransaction.setTs(format.format(new Date().getTime()));
        // 插入sell_room_transaction
        if (sellRoomTransactionMapper.doSellRoomTransaction(sellRoomTransaction) == 0) {
            logger.warn("SellRoomService.doSellRoom[sellRoomTransactionMapper.doSellRoomTransaction" +
                    "(sellRoomTransaction)]有误");
            throw new Exception("交易属性插入失败");
        }
        sellRoom.getPkSellRoomTransaction().setPkSellRoomTransaction(pkSellRoomTransaction);
        // 配置sell_room
        String pkSellRoom = pkBuilder.getPk(sellRoom.toString());
        sellRoom.setPkSellRoom(pkSellRoom);
        sellRoom.setTs(format.format(new Date().getTime()));
        // 插入sell_room
        if (sellRoomMapper.doSellRoom(sellRoom) == 0) {
            logger.warn("SellRoomService.doSellRoom[sellRoomMapper.doSellRoom(sellRoom)]有误");
            throw new Exception("二手房插入失败");
        }
        // room_img_list为空判断
        if (imgs != null) {
            boolean flag = true;
            for (RoomImg ri : imgs) {
                if (flag) {
                    ri.setInd(1);
                    flag = false;
                }
                ri.setPkRoom(pkSellRoom);
                ri.setTitle(sellRoom.getTitle());
                // 插入room_img_list
                if (roomImgMapper.updateRoomImg(ri) == 0) {
                    logger.warn("SellRoomService.doSellRoom[roomImgMapper.updateRoomImg(ri)]有误");
                    throw new Exception("图片插入失败*");
                }
            }
        }
        logger.debug("SellRoomService.doSellRoom执行完毕");
        return pkSellRoom;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteSellRoom(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("SellRoomService.deleteSellRoom传入值为空");
            throw new Exception("数据为空");
        }
        SellRoom sellRoom = sellRoomMapper.getSellRoomByPk(pk);
        // sellRoom为空判断
        if (sellRoom == null) {
            logger.warn("SellRoomService.deleteSellRoom.sellRoom为空");
            throw new Exception("数据为空");
        }
        // 根据pk_room获取pk_room_img
        List<String> imgs = roomImgMapper.getRoomImgByRoom(pk);
        // imgs为空判断
        if (imgs.size() > 0) {
            // 批量修改room_img
            if (roomImgMapper.updateListRoomImg(imgs) == 0) {
                logger.warn("SellRoomService.deleteSellRoom[roomImgMapper.updateListRoomImg(imgs)]有误");
                throw new Exception("相片删除失败");
            }
        }
        // 修改参数
        sellRoom.setDr(1);
        sellRoom.setTs(format.format(new Date().getTime()));
        sellRoom.getPkSellPrice().setDr(1);
        sellRoom.getPkSellPrice().setTs(format.format(new Date().getTime()));
        sellRoom.getPkSellRoomBasic().setDr(1);
        sellRoom.getPkSellRoomBasic().setTs(format.format(new Date().getTime()));
        sellRoom.getPkSellRoomCharacteristic().setDr(1);
        sellRoom.getPkSellRoomCharacteristic().setTs(format.format(new Date().getTime()));
        sellRoom.getPkSellRoomCompartment().setDr(1);
        sellRoom.getPkSellRoomCompartment().setTs(format.format(new Date().getTime()));
        sellRoom.getPkSellRoomTransaction().setDr(1);
        sellRoom.getPkSellRoomTransaction().setTs(format.format(new Date().getTime()));
        // 修改sell_room
        if (sellRoomMapper.updateSellRoom(sellRoom) == 0) {
            logger.warn("SellRoomService.deleteSellRoom[]有误");
            throw new Exception("二手房删除失败");
        }
        // 修改sell_price
        if (sellPriceMapper.updateSellPrice(sellRoom.getPkSellPrice()) == 0) {
            logger.warn("SellRoomService.deleteSellRoom[sellPriceMapper.updateSellPrice" +
                    "(sellRoom.getPkSellPrice())]有误");
            throw new Exception("价格删除失败");
        }
        // 修改sell_room_basic
        if (sellRoomBasicMapper.updateSellRoomBasic(sellRoom.getPkSellRoomBasic()) == 0) {
            logger.warn("SellRoomService.deleteSellRoom[sellRoomBasicMapper.updateSellRoomBasic" +
                    "(sellRoom.getPkSellRoomBasic())]有误");
            throw new Exception("基本信息删除失败");
        }
        // 修改sell_room.characteristic
        if (sellRoomCharacteristicMapper.updateSellRoomCharacteristic(sellRoom.getPkSellRoomCharacteristic())
                == 0) {
            logger.warn("SellRoomService.deleteSellRoom[sellRoomCharacteristicMapper." +
                    "updateSellRoomCharacteristic(sellRoom.getPkSellRoomCharacteristic())]有误");
            throw new Exception("特色删除失败");
        }
        // 修改sell_room_compartment
        if (sellRoomCompartmentMapper.updateSellRoomCompartment(sellRoom.getPkSellRoomCompartment()) == 0) {
            logger.warn("SellRoomService.deleteSellRoom[sellRoomCompartmentMapper." +
                    "updateSellRoomCompartment(sellRoom.getPkSellRoomCompartment())]有误");
            throw new Exception("户型删除失败");
        }
        // compartments为空判断
        if (sellRoom.getPkSellRoomCompartment().getCompartments().size() > 0) {
            // 修改sell_compartment_info
            if (sellCompartmentInfoMapper.updateSellCompartmentInfoByCompartment(
                    sellRoom.getPkSellRoomCompartment().getPkSellRoomCompartment()) == 0) {
                logger.warn("SellRoomService.deleteSellRoom[]有误");
                throw new Exception("户型删除失败*");
            }
        }
        // 修改sell_room_transaction
        if (sellRoomTransactionMapper.updateSellRoomTransaction(sellRoom.getPkSellRoomTransaction()) == 0) {
            logger.warn("SellRoomService.deleteSellRoom[sellRoomTransactionMapper." +
                    "updateSellRoomTransaction(sellRoom.getPkSellRoomTransaction())]有误");
            throw new Exception("交易信息删除失败");
        }
        logger.debug("SellRoomService.deleteSellRoom执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#sellRoom.pkSellRoom")
    @Transactional(rollbackFor = Exception.class)
    public int updateSellRoom(SellRoom sellRoom) throws Exception {
        // 传入数据为空判断
        if (sellRoom == null) {
            logger.warn("SellRoomService.updateSellRoom传入数据为空");
            throw new Exception("数据为空");
        }
        SellPrice sellPrice = sellRoom.getPkSellPrice();
        SellRoomBasic sellRoomBasic = sellRoom.getPkSellRoomBasic();
        SellRoomCharacteristic sellRoomCharacteristic = sellRoom.getPkSellRoomCharacteristic();
        SellRoomCompartment sellRoomCompartment = sellRoom.getPkSellRoomCompartment();
        SellRoomTransaction sellRoomTransaction = sellRoom.getPkSellRoomTransaction();
        // 关联对象为空判断
        if (sellPrice == null && sellRoomBasic == null && sellRoomCompartment == null
                && sellRoomTransaction == null) {
            logger.warn("SellRoomService.updateSellRoom关联对象为空");
            throw new Exception("数据为空");
        }
        sellPrice.setTs(format.format(new Date().getTime()));
        // 修改sell_price
        if (sellPriceMapper.updateSellPrice(sellPrice) == 0) {
            logger.warn("SellRoomService.updateSellRoom[sellPriceMapper.updateSellPrice(sellPrice)]有误");
            throw new Exception("价格修改失败");
        }
        sellRoomBasic.setTs(format.format(new Date().getTime()));
        // 修改sell_room_basic
        if (sellRoomBasicMapper.updateSellRoomBasic(sellRoomBasic) == 0) {
            logger.warn("SellRoomService.updateSellRoom[sellRoomBasicMapper.updateSellRoomBasic" +
                    "(sellRoomBasic)]有误");
            throw new Exception("基本信息修改失败");
        }
        sellRoomCharacteristic.setTs(format.format(new Date().getTime()));
        // 修改sell_room_characteristic
        if (sellRoomCharacteristicMapper.updateSellRoomCharacteristic(sellRoomCharacteristic) == 0) {
            logger.warn("SellRoomService.updateSellRoom[sellRoomCharacteristicMapper." +
                    "updateSellRoomCharacteristic(sellRoomCharacteristic)]有误");
            throw new Exception("特色修改失败");
        }
        sellRoomTransaction.setTs(format.format(new Date().getTime()));
        // 修改sell_room_transaction
        if (sellRoomTransactionMapper.updateSellRoomTransaction(sellRoomTransaction) == 0) {
            logger.warn("SellRoomService.updateSellRoom[sellRoomTransactionMapper." +
                    "updateSellRoomTransaction(sellRoomTransaction)]有误");
            throw new Exception("交易属性修改失败");
        }
        sellRoom.setTs(format.format(new Date().getTime()));
        // 修改sell_room
        if (sellRoomMapper.updateSellRoom(sellRoom) == 0) {
            logger.warn("SellRoomService.updateSellRoom[sellRoomMapper.updateSellRoom(sellRoom)]有误");
            throw new Exception("二手房修改失败");
        }
        List<RoomImg> imgs = sellRoom.getImgs();
        // room_img为空判断
        if (imgs != null) {
            for (RoomImg ri : imgs) {
                ri.setPkRoom(sellRoom.getPkSellRoom());
                ri.setTitle(sellRoom.getTitle());
                ri.setTs(format.format(new Date().getTime()));
                // 改room_img
                if (roomImgMapper.updateRoomImg(ri) == 0) {
                    logger.warn("LeaseRoomService.updateSellRoom[roomImgMapper.updateRoomImg(ri)]" +
                            "有误");
                    throw new Exception("照片更改失败");
                }
            }
        }
        logger.debug("SellRoomService.updateSellRoom执行完毕");
        return 1;
    }

    @Override
    public List<SellRoom> getListSellRoom(SellRoom sellRoom, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return sellRoomMapper.getListSellRoom(sellRoom);
    }

    @Override
    @Cacheable(key = "#pk")
    public SellRoom getSellRoomByPk(String pk) {
        return sellRoomMapper.getSellRoomByPk(pk);
    }

    @Override
    public List<SellCompartmentType> getAllSellCompartmentType() {
        return sellCompartmentTypeMapper.getAllSellCompartmentType();
    }

    @Override
    public PageInfo<SellRoom> getListSellRoomPage(SellRoom sellRoom) {
        Page page = PageHelper.startPage(1, 10);
        sellRoomMapper.getListSellRoom(sellRoom);
        PageInfo<SellRoom> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public SellRoomCompartment getImgSellRoomCompartmentByPk(String pk) {
        return sellRoomCompartmentMapper.getImgSellRoomCompartmentByPk(pk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doSellRoomCompartment(SellRoomCompartment sellRoomCompartment) throws Exception {
        // 参数判断
        if (sellRoomCompartment == null) {
            logger.warn("SellRoomService.doSellRoomCompartment[参数为空]");
            throw new Exception("参数为空");
        }
        String pk = pkBuilder.getPk(sellRoomCompartment.toString());
        sellRoomCompartment.setPkSellRoomCompartment(pk);
        sellRoomCompartment.setTs(format.format(new Date().getTime()));
        // 插入判断
        if (sellRoomCompartmentMapper.doSellRoomCompartment(sellRoomCompartment) == 0) {
            logger.warn("SellRoomService.doSellRoomCompartment[sellRoomCompartmentMapper.doSellRoomCompartment(sellRoomCompartment)有误]");
            throw new Exception("插入图片失败");
        }
        logger.debug("SellRoomService.doSellRoomCompartment[执行完毕]");
        return pk;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doCompartmentInfo(SellRoom sellRoom) throws Exception {
        // 参数判断
        if (sellRoom == null) {
            logger.warn("SellRoomService.doCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        if (sellRoom.getPkSellRoomCompartment().getPkSellRoomCompartment() == null){
            logger.warn("SellRoomService.doCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        if (sellRoom.getPkSellRoomCompartment().getCompartments() == null){
            logger.warn("SellRoomService.doCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        List<SellCompartmentInfo> sellCompartmentInfos = sellRoom.getPkSellRoomCompartment().getCompartments();
        // 该条compartment是否为空
        for (SellCompartmentInfo sellCompartmentInfo : sellCompartmentInfos) {
            if (sellCompartmentInfo.getSpare1() != null && !"".equals(sellCompartmentInfo.getSpare1())){
                sellCompartmentInfo.setPkSellCompartmentInfo(pkBuilder.getPk(sellCompartmentInfo.toString()));
                sellCompartmentInfo.setTs(format.format(new Date().getTime()));
                sellCompartmentInfo.setPkSellRoomCompartment(sellRoom.getPkSellRoomCompartment().getPkSellRoomCompartment());
                // 插入compartmentInfo
                if (sellCompartmentInfoMapper.doSellCompartmentInfo(sellCompartmentInfo) == 0) {
                    logger.warn("SellRoomService.doCompartmentInfo[sellCompartmentInfoMapper.doSellCompartmentInfo(sellCompartmentInfo)有误]");
                    throw new Exception("info插入失败");
                }
            }
        }
        // compartment插入sell_room
        if (sellRoomMapper.updateCompartment(sellRoom) == 0) {
            logger.warn("SellRoomService.doCompartmentInfo[sellRoomMapper.updateCompartment(sellRoom)有误]");
            throw new Exception("插入失败");
        }
        logger.debug("SellRoomService.doCompartmentInfo[执行完毕]");
        return 1;
    }

    @Override
    @CacheEvict(key = "#sellRoom.pkSellRoom")
    @Transactional(rollbackFor = Exception.class)
    public int updateCompartmentInfo(SellRoom sellRoom) throws Exception {
        // 参数判断
        if(sellRoom == null){
            logger.warn("SellRoomService.updateCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        // 参数判断
        if(sellRoom.getPkSellRoomCompartment() == null){
            logger.warn("SellRoomService.updateCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        // 参数判断
        if(sellRoom.getPkSellRoomCompartment().getCompartments() == null){
            logger.warn("SellRoomService.updateCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        // sell_room修改compartment
        sellRoom.setTs(format.format(new Date().getTime()));
        if (sellRoomMapper.updateCompartment(sellRoom) == 0) {
            logger.warn("SellRoomService.updateCompartmentInfo[sellRoomMapper.updateCompartment(sellRoom)有误]");
            throw new Exception("插入失败");
        }
        List<SellCompartmentInfo> sellCompartmentInfos = sellRoom.getPkSellRoomCompartment().getCompartments();
        for (SellCompartmentInfo sellCompartmentInfo : sellCompartmentInfos){
            // 该条compartment是否为空
            if (sellCompartmentInfo.getSpare1() != null && !"".equals(sellCompartmentInfo.getSpare1())){
                sellCompartmentInfo.setPkSellRoomCompartment(sellRoom.getPkSellRoomCompartment().getPkSellRoomCompartment());
                sellCompartmentInfo.setTs(format.format(new Date().getTime()));
                // 是否新增
                if(sellCompartmentInfo.getPkSellCompartmentInfo() == null || "".equals(sellCompartmentInfo.getPkSellCompartmentInfo())){
                    sellCompartmentInfo.setPkSellCompartmentInfo(pkBuilder.getPk(sellCompartmentInfo.toString()));
                    sellCompartmentInfo.setTs(format.format(new Date().getTime()));
                    // 插入判断
                    if (sellCompartmentInfoMapper.doSellCompartmentInfo(sellCompartmentInfo) == 0){
                        logger.warn("SellRoomService.updateCompartmentInfo[sellCompartmentInfoMapper.doSellCompartmentInfo(sellCompartmentInfo)有误]");
                        throw new Exception("新增compartment有误");
                    }
                }else {
                    // 修改判断
                    if (sellCompartmentInfoMapper.updateSellCompartmentInfo(sellCompartmentInfo) == 0){
                        logger.warn("SellRoomService.updateCompartmentInfo[sellCompartmentInfoMapper.updateSellCompartmentInfo(sellCompartmentInfo)有误]");
                        throw new Exception("修改compartment有误");
                    }
                }
            }
        }
        logger.debug("SellRoomService.updateCompartmentInfo[执行完毕]");
        return 1;
    }

    @Override
    public SellRoom getSellRoomByTitle(String title) {
        return sellRoomMapper.getSellRoomByTitle(title);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public int deleteSellRoomCompartmentInfo(String pk) throws Exception {
        // 参数判断
        if (pk == null || "".equals(pk)) {
            logger.warn("SellRoomService.deleteSellRoomCompartmentInfo[参数为空]");
            throw new Exception("参数为空");
        }
        SellCompartmentInfo sellCompartmentInfo = new SellCompartmentInfo();
        sellCompartmentInfo.setPkSellCompartmentInfo(pk);
        sellCompartmentInfo.setTs(format.format(new Date().getTime()));
        sellCompartmentInfo.setDr(1);
        // 更新判断
        if (sellCompartmentInfoMapper.updateSellCompartmentInfo(sellCompartmentInfo) == 0) {
            logger.warn("SellRoomService.deleteSellRoomCompartmentInfo[sellCompartmentInfoMapper.updateSellCompartmentInfo(sellCompartmentInfo)有误]");
            throw new Exception("更改失败");
        }
        logger.debug("SellRoomService.deleteSellRoomCompartmentInfo[执行完毕]");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#sellRoom.pkSellRoom")
    public int updateSellRoomStatus(SellRoom sellRoom) throws Exception {
        // 参数判断
        if (sellRoom == null){
            logger.warn("SellRoomService.updateSellRoomStatus[参数为空]");
            throw new Exception("参数为空");
        }
        sellRoom.setTs(format.format(new Date().getTime()));
        // 修改判断
        if (sellRoomMapper.updateSellRoomStatus(sellRoom) == 0){
            logger.warn("SellRoomService.updateSellRoomStatus[sellRoomMapper.updateSellRoomStatus(sellRoom)有误]");
            throw new Exception("更改失败");
        }
        logger.debug("SellRoomService.updateSellRoomStatus[执行完毕]");
        return 1;
    }
}
