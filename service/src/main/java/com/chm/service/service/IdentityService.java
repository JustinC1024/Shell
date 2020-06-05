package com.chm.service.service;

import com.chm.service.pojo.record.Identity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 身份认证
 */
public interface IdentityService {
    /**
     * 增
     * @param identity
     * @return
     * @throws Exception
     */
    public int doIdentity(Identity identity) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteIdentity(String pk) throws Exception;

    /**
     * 改
     * @param identity
     * @return
     * @throws Exception
     */
    public int updateIdentity(Identity identity)throws Exception;

    /**
     * 查
     * @param identity
     * @param nowPage
     * @return
     */
    public List<Identity> getListIdentity(Identity identity, int nowPage);

    /**
     * 页码
     * @param identity
     * @return
     */
    public PageInfo<Identity> getListidentityPage(Identity identity);

    /**
     * 查（pkCustomer.account）
     * @param account
     * @return
     */
    public Identity getIdentityByCustomer(String account);
}
