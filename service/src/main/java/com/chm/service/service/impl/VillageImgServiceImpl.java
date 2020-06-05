package com.chm.service.service.impl;

import com.chm.service.mapper.building.VillageImgMapper;
import com.chm.service.pojo.building.VillageImg;
import com.chm.service.util.PkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "villageImg")
public class VillageImgServiceImpl implements com.chm.service.service.VillageImgService {

    @Autowired
    private VillageImgMapper villageImgMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Cacheable(key = "#pk")
    public VillageImg getImgVillageImgByPk(String pk) {
        return villageImgMapper.getVillageImgByPk(pk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doVillageImg(VillageImg villageImg) throws Exception {
        // 参数判断
        if (villageImg == null) {
            logger.warn("VillageImgService.doVillageImg[参数为空]");
            throw new Exception("参数为空");
        }
        String pk = pkBuilder.getPk(villageImg.toString());
        villageImg.setPkVillageImg(pk);
        villageImg.setTs(format.format(new Date().getTime()));
        // 插入判断
        if (villageImgMapper.doVillageImg(villageImg) == 0) {
            logger.warn("VillageImgService.doVillageImg[villageImgMapper.doListVillageImg(villageImgs)有误]");
            throw new Exception("插入图片失败");
        }
        logger.debug("VillageImgService.doVillageImg[执行完毕]");
        return pk;
    }

    @Override
    @CacheEvict(key ="#pk" )
    @Transactional(rollbackFor = Exception.class)
    public int deleteVillageImg(String pk) throws Exception {
        if(pk == null || "".equals(pk)){
            logger.warn("VillageImgService.deleteVillageImg[参数为空]");
            throw new Exception("参数为空");
        }
        VillageImg villageImg = new VillageImg();
        villageImg.setPkVillageImg(pk);
        villageImg.setTs(format.format(new Date().getTime()));
        villageImg.setDr(1);
        if(villageImgMapper.updateVillageImg(villageImg) == 0){
            logger.warn("VillageImgService.deleteVillageImg[villageImgMapper.updateVillageImg(villageImg)有误]");
            throw new Exception("更改失败");
        }
        return 1;
    }

}
