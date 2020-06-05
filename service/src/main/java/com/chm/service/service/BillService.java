package com.chm.service.service;

import com.chm.service.pojo.record.Bill;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 订单
 */
public interface BillService {

    /**
     * 增
     * @param bill
     * @return
     * @throws Exception
     */
    public int doBill(Bill bill) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteBill(String pk) throws Exception;

    /**
     * 改
     * @param bill
     * @return
     * @throws Exception
     */
    public int updateBill(Bill bill) throws Exception;

    /**
     * 查（列表）
     * @param bill
     * @param nowPage
     * @return
     */
    public List<Bill> getListBill(Bill bill, int nowPage);

    /**
     * 查（单个）
     * @param pk
     * @return
     */
    public Bill getBill(String pk);

    /**
     * 页码
     * @param bill
     * @return
     */
    public PageInfo<Bill> getListBillPage(Bill bill);


}
