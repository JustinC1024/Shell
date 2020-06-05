package com.chm.service.mapper.record;

import com.chm.service.pojo.record.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {

    /**
     * 增
     * @param bill
     * @return
     */
    public int doBill(Bill bill);

    /**
     * 改
     * @param bill
     * @return
     */
    public int updateBill(Bill bill);

    /**
     * 查（status/ts/pkSupport.title/pkLeaseRoom.title/pkSellRoom.title/pkCustomer.name/pkClerk.name）
     * @param bill
     * @return
     */
    public List<Bill> getListBill(Bill bill);

    /**
     * 查（pk）
     * @param pk
     * @return
     */
    public Bill getBillByPk(String pk);

}
