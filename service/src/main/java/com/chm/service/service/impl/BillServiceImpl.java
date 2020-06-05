package com.chm.service.service.impl;

import com.chm.service.mapper.record.BillMapper;
import com.chm.service.pojo.record.Bill;
import com.chm.service.service.BillService;
import com.chm.service.util.PkBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "bill")
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doBill(Bill bill) throws Exception {
        // 传入值为空判断
        if (bill == null) {
            logger.warn("ClerkService.doBill传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置bill
        bill.setPkBill(pkBuilder.getPk(bill.toString()));
        bill.setTs(format.format(new Date().getTime()));
        // 插入bill
        if (billMapper.doBill(bill) == 0) {
            logger.warn("BillService.doBill[billMapper.doBill(bill)]有误");
            throw new Exception("订单插入失败");
        }
        logger.debug("BillService.doBill执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteBill(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("BillService.deleteBill传入值为空");
            throw new Exception("传入值为空");
        }
        Bill bill = billMapper.getBillByPk(pk);
        // bill为空判断
        if (bill == null) {
            logger.warn("BillService.deleteBill.bill为空");
            throw new Exception("数据为空");
        }
        // 数据处理
        bill.setDr(1);
        bill.setTs(format.format(new Date().getTime()));
        // 修改bill
        if (billMapper.updateBill(bill) == 0) {
            logger.warn("BillService.deleteClerk[billMapper.updateBill(bill)]有误");
            throw new Exception("订单删除失败");
        }
        logger.debug("BillService.deleteBill执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#bill.pkLeaseRoom")
    @Transactional(rollbackFor = Exception.class)
    public int updateBill(Bill bill) throws Exception {
        // 传入值为空判断
        if (bill == null) {
            logger.warn("ClerkService.updateBill传入值为空");
            throw new Exception("传入值为空");
        }
        bill.setTs(format.format(new Date().getTime()));
        // 修改clerk
        if (billMapper.updateBill(bill) == 0) {
            logger.warn("BillService.updateBill[billMapper.updateBill(bill)]有误");
            throw new Exception("订单修改失败");
        }
        logger.debug("BillService.updateBill执行完毕");
        return 1;
    }

    @Override
    public List<Bill> getListBill(Bill bill, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return billMapper.getListBill(bill);
    }

    @Override
    @Cacheable(key = "#pk")
    public Bill getBill(String pk) {
        return billMapper.getBillByPk(pk);
    }

    @Override
    public PageInfo<Bill> getListBillPage(Bill bill) {
        Page page = PageHelper.startPage(1, 10);
        billMapper.getListBill(bill);
        PageInfo<Bill> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }
}
