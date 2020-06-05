package com.chm.service.mapper.record;

import com.chm.service.pojo.record.Owner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OwnerMapper {

    /**
     * 增
     * @param owner
     * @return
     */
    public int doOwner(Owner owner);

    /**
     * 删
     * @param dr
     * @param ts
     * @return
     */
    public int deleteOwner(@Param("dr") int dr, @Param("ts") String ts);

    /**
     * 改
     * @param owner
     * @return
     */
    public int updateOwner(Owner owner);

    /**
     * 查（status/ts/pkLeaseRoom.title/pkSellRoom.title/pkCustomer.name/pkClerk.name）
     * @param owner
     * @return
     */
    public List<Owner> getListOwner(Owner owner);

    /**
     * 查（pkLeaseRoom.pkLeaseRoom/pkSellRoom.pkSellRoom）
     * @param pk
     * @param type
     * @return
     */
    public Owner getOwnerByRoom(@Param("pk") String pk, @Param("type") String type);

    /**
     * 删（sellRoom/leaseRoom）
     * @param owner
     * @return
     */
    public int updateOwnerByRoom(Owner owner);
}
