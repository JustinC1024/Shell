package com.chm.service.service.impl;

import com.chm.service.mapper.record.FollowMapper;
import com.chm.service.pojo.record.Follow;
import com.chm.service.service.FollowService;
import com.chm.service.util.PkBuilder;
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
@CacheConfig(cacheNames = "follow")
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doFollow(Follow follow) throws Exception {
        // 传入值为空判断
        if (follow == null) {
            logger.warn("FollowService.doFollow传入值为空");
            throw new Exception("传入值为空");
        }
        // 数据处理
        follow.setDr(1);
        List<Follow> follows = followMapper.getListFollow(follow);
        // follow是否已存在
        if (follows.size() > 0){
            follow.setDr(0);
            followMapper.updateFollow(follow);
        }else {
            // 配置follow
            follow.setPkFollow(pkBuilder.getPk(follow.toString()));
            follow.setTs(format.format(new Date().getTime()));
            // 插入follow
            if (followMapper.doFollow(follow) == 0) {
                logger.warn("FollowService.doFollow[followMapper.doFollow(follow)]有误");
                throw new Exception("关注插入失败");
            }
            logger.debug("FollowService.doFollow执行完毕");
        }
        return 1;
    }

    @Override
    @CacheEvict(key = "#follow.pkFollow")
    @Transactional(rollbackFor = Exception.class)
    public int deleteFollow(Follow follow) throws Exception {
        // 传入值为空判断
        if (follow == null) {
            logger.warn("FollowService.deleteFollow传入值为空");
            throw new Exception("传入值为空");
        }
        // 数据处理
        follow.setDr(1);
        follow.setTs(format.format(new Date().getTime()));
        // 修改follow
        if (followMapper.updateFollow(follow) == 0) {
            logger.warn("FollowService.deleteFollow[followMapper.updateFollow(follow)]有误");
            throw new Exception("关注删除失败");
        }
        logger.debug("FollowService.deleteFollow执行完毕");
        return 1;
    }

    @Override
    @Cacheable(key = "#follow")
    public List<Follow> getListFollow(Follow follow) {
        return followMapper.getListFollow(follow);
    }
}
