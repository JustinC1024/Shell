package com.chm.service.service.impl;

import com.chm.service.mapper.building.VillageImgMapper;
import com.chm.service.mapper.building.VillageMapper;
import com.chm.service.pojo.building.Village;
import com.chm.service.pojo.building.VillageImg;
import com.chm.service.service.VillageService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "village")
public class VillageServiceImpl implements VillageService {

    @Autowired
    private VillageMapper villageMapper;
    @Autowired
    private VillageImgMapper villageImgMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doVillage(Village village) throws Exception {
        // 传入值为空判断
        if (village == null) {
            logger.warn("VillageService.doVillage传入值为空");
            throw new Exception("数据为空");
        }
        // 初始化数据
        VillageImg img = village.getImg();
        List<VillageImg> imgs = village.getImgs();
        // 配置village
        String pkVillage = pkBuilder.getPk(village.toString());
        village.setPkVillage(pkVillage);
        village.setTs(format.format(new Date().getTime()));
        // 插入village
        if (villageMapper.doVillage(village) == 0){
            logger.warn("VillageService.doVillage[villageMapper.doVillage(village)]有误");
            throw new Exception("小区插入失败");
        }
        // village_img_list为空判断
        if (imgs != null) {
            boolean flag = true;
            for (VillageImg vi : imgs) {
                if(flag){
                    vi.setInd(1);
                    flag = false;
                }
                vi.setPkVillage(pkVillage);
                // 插入village_img_list
                if(villageImgMapper.updateVillageImg(vi) == 0) {
                    logger.warn("VillageService.doVillage[villageImgMapper.updateVillageImg(vi)]有误");
                    throw new Exception("图片插入失败");
                }
            }
        }
        logger.debug("VillageService.doVillage执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteVillage(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("VillageService.deleteVillage传入值为空");
            throw new Exception("数据为空");
        }
        // 根据pk_village获取pk_village_img
        Village village = villageMapper.getVillageByPk(pk);
        // village_img为空判断
        if (village.getImgs() != null) {
            // 修改village_img
            if (villageImgMapper.updateVillageImgByVillage(pk) == 0) {
                logger.warn("VillageService.deleteVillage[villageImgMapper.updateVillageImgByVillage" +
                        "(pk)]有误");
                throw new Exception("照片删除失败");
            }
        }
        // 修改参数
        village.setDr(1);
        village.setTs(format.format(new Date().getTime()));
        // 修改village
        if (villageMapper.updateVillage(village) == 0) {
            logger.warn("VillageService.deleteVillage[villageMapper.updateVillage(village)]有误");
            throw new Exception("小区删除失败");
        }
        logger.debug("VillageService.deleteVillage执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#village.pkVillage")
    @Transactional(rollbackFor = Exception.class)
    public int updateVillage(Village village) throws Exception {
        // 传入值为空判断
        if (village == null) {
            logger.warn("VillageService.updateVillage传入值为空");
            throw new Exception("数据为空");
        }
        village.setTs(format.format(new Date().getTime()));
        // 修改village
        if (villageMapper.updateVillage(village) == 0) {
            logger.warn("VillageService.updateVillage[villageMapper.updateVillage(village)]有误");
            throw new Exception("小区更改失败");
        }
        List<VillageImg> imgs = village.getImgs();
        // village_img为空判断
        if (imgs != null) {
            for (VillageImg vi : imgs) {
                vi.setPkVillage(village.getPkVillage());
                vi.setTs(format.format(new Date().getTime()));
                if(villageImgMapper.updateVillageImg(vi) == 0){
                    logger.warn("VillageService.updateVillage[villageImgMapper.updateVillageImg(vi)]" +
                            "有误");
                    throw new Exception("照片更改失败");
                }
            }
        }
        logger.debug("VillageService.updateVillage执行完毕");
        return 1;
    }

    @Override
    public List<Village> getListVillage(Village village, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return villageMapper.getListVillage(village);
    }

    @Override
    @Cacheable(key = "#pk")
    public Village getVillageByPk(String pk) {
        return villageMapper.getVillageByPk(pk);
    }

    @Override
    public PageInfo<Village> getListVillagePage(Village village) {
        Page page = PageHelper.startPage(1, 10);
        villageMapper.getListVillage(village);
        PageInfo<Village> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public Village getVillageByTitle(String title) {
        return villageMapper.getVillageByTitle(title);
    }
}
