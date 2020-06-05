package com.chm.service.mapper.record;

import com.chm.service.pojo.record.Identity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdentityMapper {

    /**
     * 增
     * @param identity
     * @return
     */
    public int doIdentity(Identity identity);

    /**
     * 删
     * @param dr
     * @param ts
     * @return
     */
    public int deleteIdentity(@Param("dr") int dr, @Param("ts") String ts);

    /**
     * 改
     * @param identity
     * @return
     */
    public int updateIdentity(Identity identity);

    /**
     * 查（status/ts/pkCustomer.name/pkClerk.name）
     * @param identity
     * @return
     */
    public List<Identity> getListIdentity(Identity identity);

    /**
     * 查（pkCustomer.account）
     * @param account
     * @return
     */
    public Identity getIdentityByCustomer(String account);

}
