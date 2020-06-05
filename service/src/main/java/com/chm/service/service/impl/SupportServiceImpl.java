package com.chm.service.service.impl;

import com.chm.service.mapper.human.SupportMapper;
import com.chm.service.pojo.human.Support;
import com.chm.service.service.SupportService;
import com.chm.service.util.PkBuilder;
import com.github.pagehelper.PageHelper;
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
public class SupportServiceImpl implements SupportService {

    @Autowired
    private SupportMapper supportMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doSupport(Support support) throws Exception {
        // 传入值为空判断
        if (support == null) {
            logger.warn("SupportService.doSupport传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置support
        support.setPkSupport(pkBuilder.getPk(support.toString()));
        support.setTs(format.format(new Date().getTime()));
        // 插入support
        if (supportMapper.doSupport(support) == 0) {
            logger.warn("SupportService.doSupport[supportMapper.doSupport(support)]有误");
            throw new Exception("服务插入失败");
        }
        logger.debug("SupportService.doSupport执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSupport(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("SupportService.deleteSupport传入值为空");
            throw new Exception("传入值为空");
        }
        Support support = supportMapper.getSupportByPk(pk);
        // support为空判断
        if (support == null) {
            logger.warn("SupportService.deleteSupport.support为空");
            throw new Exception("数据为空");
        }
        // 数据处理
        support.setDr(1);
        support.setTs(format.format(new Date().getTime()));
        // 修改support
        if (supportMapper.updateSupport(support) == 0) {
            logger.warn("SupportService.deleteSupport[supportMapper.updateSupport(support)]有误");
            throw new Exception("服务删除失败");
        }
        logger.debug("SupportService.deleteSupport执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSupport(Support support) throws Exception {
        // 传入值为空判断
        if (support == null) {
            logger.warn("SupportService.updateSupport传入值为空");
            throw new Exception("传入值为空");
        }
        support.setTs(format.format(new Date().getTime()));
        // 修改support
        if (supportMapper.updateSupport(support) == 0) {
            logger.warn("SupportService.updateSupport[supportMapper.updateSupport(support)]有误");
            throw new Exception("服务修改失败");
        }
        logger.debug("SupportService.updateSupport执行完毕");
        return 1;
    }

    @Override
    public List<Support> getListSupport(String title, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return supportMapper.getListSupport(title);
    }

    @Override
    public Support getSupport(String pk) {
        return supportMapper.getSupportByPk(pk);
    }
}
