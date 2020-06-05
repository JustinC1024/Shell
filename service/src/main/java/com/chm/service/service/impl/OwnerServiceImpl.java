package com.chm.service.service.impl;

import com.chm.service.mapper.record.OwnerMapper;
import com.chm.service.pojo.record.Owner;
import com.chm.service.service.OwnerService;
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

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doOwner(Owner owner) throws Exception {
        // 传入值为空判断
        if (owner == null) {
            logger.warn("OwnerService.doOwner传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置owner
        owner.setPkOwner(pkBuilder.getPk(owner.toString()));
        owner.setTs(format.format(new Date().getTime()));
        // 插入owner
        if (ownerMapper.doOwner(owner) == 0) {
            logger.warn("OwnerService.doOwner[ownerMapper.doOwner(owner)]有误");
            throw new Exception("卖家插入失败");
        }
        logger.debug("OwnerService.doOwner执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOwner(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("OwnerService.deleteOwner传入值为空");
            throw new Exception("传入值为空");
        }
        // 数据处理
        Owner owner = new Owner();
        owner.setPkOwner(pk);
        owner.setDr(1);
        owner.setTs(format.format(new Date().getTime()));
        // 修改clerk
        if (ownerMapper.updateOwner(owner) == 0) {
            logger.warn("OwnerService.deleteOwner[ownerMapper.updateOwner(owner)]有误");
            throw new Exception("卖家删除失败");
        }
        logger.debug("OwnerService.deleteOwner执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOwner(Owner owner) throws Exception {
        // 传入值为空判断
        if (owner == null) {
            logger.warn("OwnerService.updateOwner传入值为空");
            throw new Exception("传入值为空");
        }
        owner.setTs(format.format(new Date().getTime()));
        // 修改owner
        if (ownerMapper.updateOwner(owner) == 0) {
            logger.warn("OwnerService.updateOwner[ownerMapper.updateOwner(owner)]有误");
            throw new Exception("卖家修改失败");
        }
        logger.debug("OwnerService.updateOwner执行完毕");
        return 1;
    }

    @Override
    public List<Owner> getListOwner(Owner owner, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return ownerMapper.getListOwner(owner);
    }

    @Override
    public PageInfo<Owner> getListOwnerPage(Owner owner) {
        Page page = PageHelper.startPage(1, 10);
        ownerMapper.getListOwner(owner);
        PageInfo<Owner> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public Owner getOwnerByRoom(String pk, String type) {
        return ownerMapper.getOwnerByRoom(pk, type);
    }

    @Override
    public int deleteOwnerByRoom(Owner owner) throws Exception {
        // 参数判断
        if (owner == null){
            logger.warn("OwnerService.deleteOwnerByRoom[参数为空]");
            throw new Exception("参数为空");
        }
        owner.setTs(format.format(new Date().getTime()));
        // 修改owner
        if (ownerMapper.updateOwnerByRoom(owner) == 0){
            logger.warn("OwnerService.deleteOwnerByRoom[ownerMapper.updateOwnerByRoom(owner)有误]");
            throw new Exception("修改有误");
        }
        logger.debug("OwnerService.deleteOwnerByRoom[执行完毕]");
        return 1;
    }
}
