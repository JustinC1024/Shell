package com.chm.service.service.impl;

import com.chm.service.mapper.human.JurisdictionMapper;
import com.chm.service.pojo.human.Jurisdiction;
import com.chm.service.service.JurisdictionService;
import com.chm.service.util.PkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    private JurisdictionMapper jurisdictionMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doJurisdiction(Jurisdiction jurisdiction) throws Exception {
        // 传入值为空判断
        if (jurisdiction == null) {
            logger.warn("JurisdictionService.doJurisdiction传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置jurisdiction
        jurisdiction.setPkJurisdiction(pkBuilder.getPk(jurisdiction.toString()));
        jurisdiction.setTs(format.format(new Date().getTime()));
        // 插入jurisdiction
        if (jurisdictionMapper.doJurisdiction(jurisdiction) == 0) {
            logger.warn("JurisdictionService.doJurisdiction[jurisdictionMapper.doJurisdiction" +
                    "(jurisdiction)]有误");
            throw new Exception("职员插入失败");
        }
        logger.debug("JurisdictionService.doJurisdiction执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteJurisdiction(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("JurisdictionService.deleteJurisdiction传入值为空");
            throw new Exception("传入值为空");
        }
        // 修改clerk
        if (jurisdictionMapper.updateJurisdiction(pk) == 0) {
            logger.warn("JurisdictionService.deleteJurisdiction[jurisdictionMapper.updateJurisdiction" +
                    "(pk)]有误");
            throw new Exception("职员删除失败");
        }
        logger.debug("JurisdictionService.deleteJurisdiction执行完毕");
        return 1;
    }

    @Override
    public List<Jurisdiction> getListJurisdiction() {
        return jurisdictionMapper.getAllJurisdiction();
    }
}
