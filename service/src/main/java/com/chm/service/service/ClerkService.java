package com.chm.service.service;

import com.chm.service.pojo.human.Clerk;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 平台职员
 */
public interface ClerkService {

    /**
     * 增
     * @param clerk
     * @return
     * @throws Exception
     */
    public int doClerk(Clerk clerk) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteClerk(String pk) throws Exception;

    /**
     * 改
     * @param clerk
     * @return
     * @throws Exception
     */
    public int updateClerk(Clerk clerk) throws Exception;

    /**
     * 查（列表）
     * @param clerk
     * @param nowPage
     * @return
     */
    public List<Clerk> getListClerk(Clerk clerk, int nowPage);

    /**
     * 查（单个）
     * @param pk
     * @return
     */
    public Clerk getClerk(String pk);

    /**
     * 查（account）
     * @param account
     * @return
     */
    public Clerk getClerkByAccount(String account);

    /**
     * 页码
     * @param clerk
     * @return
     */
    public PageInfo<Clerk> getListClerkPage(Clerk clerk);

    /**
     * 查
     * @return
     */
    public List<Clerk> getAllClerk();
}
