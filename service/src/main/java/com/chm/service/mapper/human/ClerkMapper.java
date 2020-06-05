package com.chm.service.mapper.human;

import com.chm.service.pojo.human.Clerk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClerkMapper {

    /**
     * 增
     * @param clerk
     * @return
     */
    public int doClerk(Clerk clerk);

    /**
     * 删
     * @param dr
     * @param ts
     * @return
     */
    public int deleteClerk(@Param("dr") int dr, @Param("ts") String ts);

    /**
     * 改
     * @param clerk
     * @return
     */
    public int updateClerk(Clerk clerk);

    /**
     * 查（name/phone/account/jurisdiction/parent/ts）
     * @param clerk
     * @return
     */
    public List<Clerk> getListClerk(Clerk clerk);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public Clerk getClerkByPk(String pk);

    /**
     * 查（account）
     * @param account
     * @return
     */
    public Clerk getClerkByAccount(String account);

    /**
     * 查
     * @return
     */
    public List<Clerk> getAllClerk();
}
