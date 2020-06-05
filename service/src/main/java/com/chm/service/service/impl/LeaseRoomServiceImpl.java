package com.chm.service.service.impl;

import com.chm.service.mapper.building.*;
import com.chm.service.pojo.building.*;
import com.chm.service.service.LeaseRoomService;
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

@CacheConfig(cacheNames = "lease")
@Service
public class LeaseRoomServiceImpl implements LeaseRoomService {

    @Autowired
    private LeasePriceMapper leasePriceMapper;
    @Autowired
    private LeaseRoomBasicMapper leaseRoomBasicMapper;
    @Autowired
    private LeaseRoomMapper leaseRoomMapper;
    @Autowired
    private LeaseRoomMatingMapper leaseRoomMatingMapper;
    @Autowired
    private RoomImgMapper roomImgMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doLeaseRoom(LeaseRoom leaseRoom) throws Exception {
        // 传入值为空判断
        if (leaseRoom == null && (leaseRoom.getPkLeasePrice() == null || leaseRoom.getPkLeaseRoomBasic() == null
                || leaseRoom.getPkLeaseRoomMating() == null)) {
            logger.warn("LeaseRoomService.doLeaseRoom传入值为空");
            throw new Exception("数据为空");
        }
        // 初始化
        LeasePrice leasePrice = leaseRoom.getPkLeasePrice();
        LeaseRoomBasic leaseRoomBasic = leaseRoom.getPkLeaseRoomBasic();
        LeaseRoomMating leaseRoomMating = leaseRoom.getPkLeaseRoomMating();
        List<RoomImg> roomImgs = leaseRoom.getImgs();
        // 配置lease_price
        String pkLeasePrice = pkBuilder.getPk(leasePrice.toString());
        leasePrice.setPkLeasePrice(pkLeasePrice);
        leasePrice.setTs(format.format(new Date().getTime()));
        // 插入lease_price
        if (leasePriceMapper.doLeasePrice(leasePrice) == 0) {
            logger.warn("LeaseRoomService.doLeaseRoom[leasePriceMapper.doLeasePrice(leasePrice)]有误");
            throw new Exception("价格插入失败");
        }
        leaseRoom.setPkLeasePrice(leasePrice);
        // 配置lease_room_basic
        String pkLeaseRoomBasic = pkBuilder.getPk(leaseRoomBasic.toString());
        leaseRoomBasic.setPkLeaseRoomBasic(pkLeaseRoomBasic);
        leaseRoomBasic.setTs(format.format(new Date().getTime()));
        // 插入lease_room_basic
        if (leaseRoomBasicMapper.doLeaseRoomBasic(leaseRoomBasic) == 0) {
            logger.warn("LeaseRoomService.doLeaseRoom[leaseRoomBasicMapper.doLeaseRoomBasic" +
                    "(leaseRoomBasic)]有误");
            throw new Exception("基本信息插入失败");
        }
        leaseRoom.setPkLeaseRoomBasic(leaseRoomBasic);
        // 配置lease_room_mating
        String pkLeaseRoomMating = pkBuilder.getPk(leaseRoomMating.toString());
        leaseRoomMating.setPkLeaseRoomMating(pkLeaseRoomMating);
        leaseRoomMating.setTs(format.format(new Date().getTime()));
        // 插入lease_room_mating
        if (leaseRoomMatingMapper.doLeaseRoomMating(leaseRoomMating) == 0) {
            logger.warn("LeaseRoomService.doLeaseRoom[leaseRoomMatingMapper.doLeaseRoomMating" +
                    "(leaseRoomMating)]有误");
            throw new Exception("配套信息插入失败");
        }
        leaseRoom.setPkLeaseRoomMating(leaseRoomMating);
        // 配置lease_room
        String pkLeaseRoom = pkBuilder.getPk(leaseRoom.toString());
        leaseRoom.setPkLeaseRoom(pkLeaseRoom);
        leaseRoom.setTs(format.format(new Date().getTime()));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        leaseRoom.setUpdateTs(format1.format(new Date().getTime()));
        // 插入lease_room
        if (leaseRoomMapper.doLeaseRoom(leaseRoom) == 0) {
            logger.warn("LeaseRoomService.doLeaseRoom[leaseRoomMapper.doLeaseRoom(leaseRoom)]有误");
            throw new Exception("租房信息插入失败");
        }
        // room_img_list为空判断
        if (roomImgs != null) {
            boolean flag = true;
            for (RoomImg ri : roomImgs) {
                if (flag) {
                    ri.setInd(1);
                    flag = false;
                }
                ri.setTitle(leaseRoom.getTitle());
                ri.setPkRoom(pkLeaseRoom);
                // 插入room_img_list
                if (roomImgMapper.updateRoomImg(ri) == 0) {
                    logger.warn("LeaseRoomService.doLeaseRoom[roomImgMapper.updateRoomImg(ri)]有误");
                    throw new Exception("相片插入失败");
                }
            }
        }
        logger.debug("LeaseRoomService.doLeaseRoom执行完毕");
        return pkLeaseRoom;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteLeaseRoom(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("LeaseRoomService.deleteLeaseRoom传入值为空");
            throw new Exception("数据为空");
        }
        // 根据pk获取lease_room
        LeaseRoom leaseRoom = leaseRoomMapper.getLeaseRoomByPk(pk);
        // lease_room为空判断
        if (leaseRoom == null) {
            logger.warn("LeaseRoomService.deleteLeaseRoom.leaseRoom为空");
            throw new Exception("数据为空");
        }
        // 根据pk_room获取pk_room_img
        List<String> imgs = roomImgMapper.getRoomImgByRoom(pk);
        // imgs为空判断
        if (imgs.size() > 0) {
            // 批量修改room_img
            if (roomImgMapper.updateListRoomImg(imgs) == 0) {
                logger.warn("LeaseRoomService.deleteLeaseRoom[roomImgMapper.updateListRoomImg(imgs)]有误");
                throw new Exception("相片删除失败");
            }
        }
        // 改dr
        leaseRoom.setDr(1);
        leaseRoom.setTs(format.format(new Date().getTime()));
        leaseRoom.getPkLeasePrice().setDr(1);
        leaseRoom.getPkLeasePrice().setTs(format.format(new Date().getTime()));
        leaseRoom.getPkLeaseRoomBasic().setDr(1);
        leaseRoom.getPkLeaseRoomBasic().setTs(format.format(new Date().getTime()));
        leaseRoom.getPkLeaseRoomMating().setDr(1);
        leaseRoom.getPkLeaseRoomMating().setTs(format.format(new Date().getTime()));
        // 改lease_room
        if (leaseRoomMapper.updateLeaseRoom(leaseRoom) == 0) {
            logger.warn("LeaseRoomService.deleteLeaseRoom[leaseRoomMapper.updateLeaseRoom(leaseRoom)]" +
                    "有误");
            throw new Exception("租房删除失败");
        }
        // 改lease_price
        if (leasePriceMapper.updateLeasePrice(leaseRoom.getPkLeasePrice()) == 0) {
            logger.warn("LeaseRoomService.deleteLeaseRoom[leasePriceMapper.updateLeasePrice" +
                    "(leaseRoom.getPkLeasePrice())]有误");
            throw new Exception("价格删除失败");
        }
        // 改lease_room_basic
        if (leaseRoomBasicMapper.updateLeaseRoomBasic(leaseRoom.getPkLeaseRoomBasic()) == 0) {
            logger.warn("LeaseRoomService.deleteLeaseRoom[leaseRoomBasicMapper.updateLeaseRoomBasic" +
                    "(leaseRoom.getPkLeaseRoomBasic())]有误");
            throw new Exception("基本信息删除失败");
        }
        // 改lease_room_mating
        if (leaseRoomMatingMapper.updateLeaseRoomMating(leaseRoom.getPkLeaseRoomMating()) == 0) {
            logger.warn("LeaseRoomService.deleteLeaseRoom[leaseRoomMatingMapper.updateLeaseRoomMating" +
                    "(leaseRoom.getPkLeaseRoomMating())]有误");
            throw new Exception("配套信息删除失败");
        }
        logger.debug("LeaseRoomService.deleteLeaseRoom执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#leaseRoom.pkLeaseRoom")
    @Transactional(rollbackFor = Exception.class)
    public int updateLeaseRoom(LeaseRoom leaseRoom) throws Exception {
        // 传入值为空判断
        if (leaseRoom == null) {
            logger.warn("LeaseRoomService.updateLeaseRoom值为空为空");
            throw new Exception("数据为空");
        }
        leaseRoom.getPkLeasePrice().setTs(format.format(new Date().getTime()));
        // 改lease_price
        if (leasePriceMapper.updateLeasePrice(leaseRoom.getPkLeasePrice()) == 0) {
            logger.warn("LeaseRoomService.updateLeaseRoom[leasePriceMapper.updateLeasePrice" +
                    "(leaseRoom.getPkLeasePrice())]有误");
            throw new Exception("价格更改失败");
        }
        leaseRoom.getPkLeaseRoomBasic().setTs(format.format(new Date().getTime()));
        // 改lease_room_basic
        if (leaseRoomBasicMapper.updateLeaseRoomBasic(leaseRoom.getPkLeaseRoomBasic()) == 0) {
            logger.warn("LeaseRoomService.updateLeaseRoom[leaseRoomBasicMapper.updateLeaseRoomBasic" +
                    "(leaseRoom.getPkLeaseRoomBasic())]有误");
            throw new Exception("基本信息更改失败");
        }
        leaseRoom.setTs(format.format(new Date().getTime()));
        // 改lease_room
        if (leaseRoomMapper.updateLeaseRoom(leaseRoom) == 0) {
            logger.warn("LeaseRoomService.updateLeaseRoom[leaseRoomMapper.updateLeaseRoom(leaseRoom)]" +
                    "有误");
            throw new Exception("租房失更改败");
        }
        leaseRoom.getPkLeaseRoomMating().setTs(format.format(new Date().getTime()));
        // 改lease_room_mating
        if (leaseRoomMatingMapper.updateLeaseRoomMating(leaseRoom.getPkLeaseRoomMating()) == 0) {
            logger.warn("LeaseRoomService.updateLeaseRoom[leaseRoomMatingMapper.updateLeaseRoomMating" +
                    "(leaseRoom.getPkLeaseRoomMating())]有误");
            throw new Exception("配套信息更改失败");
        }
        List<RoomImg> imgs = leaseRoom.getImgs();
        // room_img为空判断
        if (imgs != null) {
            for (RoomImg ri : imgs) {
                ri.setTitle(leaseRoom.getTitle());
                ri.setPkRoom(leaseRoom.getPkLeaseRoom());
                ri.setTs(format.format(new Date().getTime()));
                // 改room_img
                if (roomImgMapper.updateRoomImg(ri) == 0) {
                    logger.warn("LeaseRoomService.updateLeaseRoom[roomImgMapper.updateRoomImg(ri)]" +
                            "有误");
                    throw new Exception("照片更改失败");
                }

            }
        }
        logger.debug("LeaseRoomService.updateLeaseRoom执行完毕");
        return 1;
    }

    @Override
    public List<LeaseRoom> getListLeaseRoom(LeaseRoom leaseRoom, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return leaseRoomMapper.getListLeaseRoom(leaseRoom);
    }

    @Override
    @Cacheable(key = "#pk")
    public LeaseRoom getLeaseRoomByPk(String pk) {
        return leaseRoomMapper.getLeaseRoomByPk(pk);
    }

    @Override
    public PageInfo<LeaseRoom> getListLeaseRoomPage(LeaseRoom leaseRoom) {
        Page page = PageHelper.startPage(1, 10);
        leaseRoomMapper.getListLeaseRoom(leaseRoom);
        PageInfo<LeaseRoom> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public LeaseRoom getLeaseRoomByTitle(String title) {
        return leaseRoomMapper.getLeaseRoomByTitle(title);
    }

    @Override
    @CacheEvict(key = "#leaseRoom.pkLeaseRoom")
    @Transactional(rollbackFor = Exception.class)
    public int updateLeaseRoomStatus(LeaseRoom leaseRoom) throws Exception {
        // 参数判断
        if (leaseRoom == null){
            logger.warn("LeaseRoomService.updateLeaseRoomStatus[参数为空]");
            throw new Exception("参数为空");
        }
        leaseRoom.setTs(format.format(new Date().getTime()));
        // 更改判断
        if (leaseRoomMapper.updateLeaseRoomStatus(leaseRoom) == 0){
            logger.warn("LeaseRoomService.updateLeaseRoomStatus[leaseRoomMapper.updateLeaseRoomStatus(leaseRoom)有误]");
            throw new Exception("更改失败");
        }
        logger.debug("LeaseRoomService.updateLeaseRoomStatus[执行完毕]");
        return 1;
    }
}
