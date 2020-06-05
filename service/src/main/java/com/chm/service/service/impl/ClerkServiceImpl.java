package com.chm.service.service.impl;

import com.chm.service.mapper.human.ClerkMapper;
import com.chm.service.pojo.human.Clerk;
import com.chm.service.service.ClerkService;
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

@CacheConfig(cacheNames = "clerk")
@Service
public class ClerkServiceImpl implements ClerkService {

    @Autowired
    private ClerkMapper clerkMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doClerk(Clerk clerk) throws Exception {
        // 传入值为空判断
        if (clerk == null) {
            logger.warn("ClerkService.doClerk传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置clerk
        clerk.setPkClerk(pkBuilder.getPk(clerk.toString()));
        clerk.setTs(format.format(new Date().getTime()));
        // 插入clerk
        if (clerkMapper.doClerk(clerk) == 0) {
            logger.warn("ClerkService.doClerk[clerkMapper.doClerk(clerk)]有误");
            throw new Exception("职员插入失败");
        }
        logger.debug("ClerkService.doClerk执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteClerk(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("ClerkService.deleteClerk传入值为空");
            throw new Exception("传入值为空");
        }
        Clerk clerk = clerkMapper.getClerkByPk(pk);
        // clerk为空判断
        if (clerk == null) {
            logger.warn("ClerkService.deleteClerk.clerk为空");
            throw new Exception("数据为空");
        }
        // 数据处理
        clerk.setDr(1);
        clerk.setTs(format.format(new Date().getTime()));
        // 修改clerk
        if (clerkMapper.updateClerk(clerk) == 0) {
            logger.warn("ClerkService.deleteClerk[clerkMapper.updateClerk(clerk)]有误");
            throw new Exception("职员删除失败");
        }
        logger.debug("ClerkService.deleteClerk执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#clerk.pkClerk")
    @Transactional(rollbackFor = Exception.class)
    public int updateClerk(Clerk clerk) throws Exception {
        // 传入值为空判断
        if (clerk == null) {
            logger.warn("ClerkService.updateClerk传入值为空");
            throw new Exception("传入值为空");
        }
        clerk.setTs(format.format(new Date().getTime()));
        // 修改clerk
        if (clerkMapper.updateClerk(clerk) == 0) {
            logger.warn("ClerkService.updateClerk[clerkMapper.updateClerk(clerk)]有误");
            throw new Exception("职员修改失败");
        }
        logger.debug("ClerkService.updateClerk执行完毕");
        return 1;
    }

    @Override
    public List<Clerk> getListClerk(Clerk clerk, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return clerkMapper.getListClerk(clerk);
    }

    @Override
    @Cacheable(key = "#pk")
    public Clerk getClerk(String pk) {
        return clerkMapper.getClerkByPk(pk);
    }

    @Override
    public Clerk getClerkByAccount(String account) {
        return clerkMapper.getClerkByAccount(account);
    }

    @Override
    public PageInfo<Clerk> getListClerkPage(Clerk clerk) {
        Page page = PageHelper.startPage(1, 10);
        clerkMapper.getListClerk(clerk);
        PageInfo<Clerk> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public List<Clerk> getAllClerk() {
        return clerkMapper.getAllClerk();
    }
}
