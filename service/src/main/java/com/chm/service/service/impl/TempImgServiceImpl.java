package com.chm.service.service.impl;

import com.chm.service.mapper.human.ClerkMapper;
import com.chm.service.mapper.record.TempImgMapper;
import com.chm.service.pojo.record.TempImg;
import com.chm.service.service.TempImgService;
import com.chm.service.util.PkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "tempImg")
public class TempImgServiceImpl implements TempImgService {

    @Autowired
    private TempImgMapper tempImgMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doTempImg(TempImg tempImg) throws Exception {
        // 参数判断
        if (tempImg == null){
            logger.warn("TempImgServiceImpl.doTempImg[参数为空]");
            throw new Exception("参数为空");
        }
        // 配置tempImg
        String pkTempImg = pkBuilder.getPk(tempImg.toString());
        tempImg.setPkTempImg(pkTempImg);
        tempImg.setTs(format.format(new Date().getTime()));
        // 插入tempImg
        if (tempImgMapper.doTempImg(tempImg) == 0){
            logger.warn("TempImgServiceImpl.doTempImg[tempImgMapper.doTempImg(tempImg)有误]");
            throw new Exception("图片插入失败");
        }

        logger.debug("TempImgServiceImpl.doTempImg执行成功");
        return pkTempImg;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteTempImg(String pk) throws Exception {
        // 参数判断
        if (pk == null || "".equals(pk)){
            logger.warn("TempImgServiceImpl.deleteTempImg[参数为空]");
            throw new Exception("参数为空");
        }
        // 数据处理
        TempImg tempImg = new TempImg();
        tempImg.setPkTempImg(pk);
        tempImg.setDr(1);
        tempImg.setTs(format.format(new Date().getTime()));
        // 修改tempImg
        if (tempImgMapper.updateTempImg(tempImg) == 0){
            logger.warn("TempImgServiceImpl.deleteTempImg[tempImgMapper.updateTempImg(tempImg)有误]");
            throw new Exception("修改图片失败");
        }
        logger.debug("TempImgServiceImpl.deleteTempImg[执行成功]");
        return 1;
    }

    @Override
    @Cacheable(key = "#pk")
    public TempImg getTempImgByPk(String pk) {
        return tempImgMapper.getTempImgByPk(pk);
    }
}
