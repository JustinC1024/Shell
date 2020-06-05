package com.chm.service.service;

import com.chm.service.pojo.record.Follow;

import java.util.List;

/**
 * 关注
 */
public interface FollowService {

    /**
     * 增
     * @param follow
     * @return
     * @throws Exception
     */
    public int doFollow(Follow follow) throws Exception;

    /**
     * 删
     * @param follow
     * @return
     * @throws Exception
     */
    public int deleteFollow(Follow follow) throws Exception;

    /**
     * 查
     * @param follow
     * @return
     */
    public List<Follow> getListFollow(Follow follow);

}
