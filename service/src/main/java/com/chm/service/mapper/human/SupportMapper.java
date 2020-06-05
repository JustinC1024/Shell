package com.chm.service.mapper.human;

import com.chm.service.pojo.human.Support;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupportMapper {

    /**
     * 增
     * @param support
     * @return
     */
    public int doSupport(Support support);

    /**
     * 删
     * @param dr
     * @param ts
     * @return
     */
    public int deleteSupport(@Param("dr") int dr,@Param("ts") String ts);

    /**
     * 改
     * @param support
     * @return
     */
    public int updateSupport(Support support);

    /**
     * 查（title）
     * @param title
     * @return
     */
    public List<Support> getListSupport(String title);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public Support getSupportByPk(String pk);

}
