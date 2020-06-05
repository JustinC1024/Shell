package com.chm.service.mapper.record;

import com.chm.service.pojo.record.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowMapper {

    /**
     * 增
     * @param follow
     * @return
     */
    public int doFollow(Follow follow);

    /**
     * 改
     * @param follow
     * @return
     */
    public int updateFollow(Follow follow);

    /**
     * 查（pkCustomer.pkCustomer/pkSellRoom.pkSellRoom/pkLeaseRoom.pkLeaseRoom/dr）
     * @param follow
     * @return
     */
    public List<Follow> getListFollow(Follow follow);

}
