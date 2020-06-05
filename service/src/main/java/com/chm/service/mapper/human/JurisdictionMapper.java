package com.chm.service.mapper.human;

import com.chm.service.pojo.human.Jurisdiction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JurisdictionMapper {

    /**
     * 增
     * @param jurisdiction
     * @return
     */
    public int doJurisdiction(Jurisdiction jurisdiction);

    /**
     * 删
     * @param dr
     * @param ts
     * @return
     */
    public int deleteJurisdiction(@Param("dr") int dr, @Param("ts") String ts);

    /**
     * 改
     * @param dr
     * @return
     */
    public int updateJurisdiction(String dr);

    /**
     * 查
     * @return
     */
    public List<Jurisdiction> getAllJurisdiction();

}
