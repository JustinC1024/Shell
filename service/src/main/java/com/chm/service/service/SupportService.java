package com.chm.service.service;

import com.chm.service.pojo.human.Support;

import java.util.List;

/**
 * 服务
 */
public interface SupportService {

    /**
     * 增
     * @param support
     * @return
     * @throws Exception
     */
    public int doSupport(Support support) throws Exception;

    /**
     * 删
     * @param support
     * @return
     * @throws Exception
     */
    public int deleteSupport(String support) throws Exception;

    /**
     * 改
     * @param pk
     * @return
     * @throws Exception
     */
    public int updateSupport(Support pk) throws Exception;

    /**
     * 查（列表）
     * @param title
     * @param nowPage
     * @return
     */
    public List<Support> getListSupport(String title, int nowPage);

    /**
     * 查（单个）
     * @param pk
     * @return
     */
    public Support getSupport(String pk);

}
