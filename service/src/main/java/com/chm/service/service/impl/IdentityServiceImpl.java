package com.chm.service.service.impl;

import com.chm.service.mapper.record.IdentityMapper;
import com.chm.service.pojo.record.Identity;
import com.chm.service.service.IdentityService;
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
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private IdentityMapper identityMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doIdentity(Identity identity) throws Exception {
        // 传入值为空判断
        if (identity == null) {
            logger.warn("IdentityService.doIdentity传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置clerk
        identity.setPkIdentity(pkBuilder.getPk(identity.toString()));
        identity.setTs(format.format(new Date().getTime()));
        // 插入clerk
        if (identityMapper.doIdentity(identity) == 0) {
            logger.warn("IdentityService.doIdentity[identityMapper.doClerk(identity)]有误");
            throw new Exception("身份认证插入失败");
        }
        logger.debug("IdentityService.doIdentity执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteIdentity(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("IdentityService.deleteIdentity传入值为空");
            throw new Exception("传入值为空");
        }
        // 数据处理
        Identity identity = new Identity();
        identity.setPkIdentity(pk);
        identity.setDr(1);
        identity.setTs(format.format(new Date().getTime()));
        // 修改identity
        if (identityMapper.updateIdentity(identity) == 0) {
            logger.warn("IdentityService.deleteIdentity[identityMapper.updateIdentity(identity)]有误");
            throw new Exception("身份认证删除失败");
        }
        logger.debug("IdentityService.deleteIdentity执行完毕");
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateIdentity(Identity identity) throws Exception {
        // 传入值为空判断
        if (identity == null) {
            logger.warn("IdentityService.updateIdentity传入值为空");
            throw new Exception("传入值为空");
        }
        identity.setTs(format.format(new Date().getTime()));
        // 修改identity
        if (identityMapper.updateIdentity(identity) == 0) {
            logger.warn("IdentityService.updateIdentity[identityMapper.updateIdentity(identity)]有误");
            throw new Exception("身份认证修改失败");
        }
        logger.debug("IdentityService.updateIdentity执行完毕");
        return 1;
    }

    @Override
    public List<Identity> getListIdentity(Identity identity, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return identityMapper.getListIdentity(identity);
    }

    @Override
    public PageInfo<Identity> getListidentityPage(Identity identity) {
        Page page = PageHelper.startPage(1, 10);
        identityMapper.getListIdentity(identity);
        PageInfo<Identity> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public Identity getIdentityByCustomer(String account) {
        return identityMapper.getIdentityByCustomer(account);
    }
}
