package com.chm.service.service;

import com.chm.service.pojo.human.Jurisdiction;

import java.util.List;

/**
 * 权限
 */
public interface JurisdictionService {

    /**
     * 增
     * @param jurisdiction
     * @return
     * @throws Exception
     */
    public int doJurisdiction(Jurisdiction jurisdiction) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteJurisdiction(String pk) throws Exception;

    /**
     * 查
     * @return
     */
    public List<Jurisdiction> getListJurisdiction();

}
