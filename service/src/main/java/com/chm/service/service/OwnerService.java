package com.chm.service.service;

import com.chm.service.pojo.record.Owner;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 卖家
 */
public interface OwnerService {
    /**
     * 增
     * @param owner
     * @return
     * @throws Exception
     */
    public int doOwner(Owner owner) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteOwner(String pk) throws Exception;

    /**
     * 改
     * @param owner
     * @return
     * @throws Exception
     */
    public int updateOwner(Owner owner) throws Exception;

    /**
     * 查
     * @param owner
     * @param nowPage
     * @return
     */
    public List<Owner> getListOwner(Owner owner, int nowPage);

    /**
     * 页码
     * @param owner
     * @return
     */
    public PageInfo<Owner> getListOwnerPage(Owner owner);

    /**
     * 查pk
     * @param pk
     * @param type
     * @return
     */
    public Owner getOwnerByRoom(String pk, String type);

    /**
     * 删（room）
     * @param owner
     * @return
     * @throws Exception
     */
    public int deleteOwnerByRoom(Owner owner) throws Exception;

}
