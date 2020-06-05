package com.chm.service.service.impl;

import com.chm.service.mapper.human.CustomerMapper;
import com.chm.service.pojo.human.Customer;
import com.chm.service.service.CustomerService;
import com.chm.service.util.PkBuilder;
import com.github.pagehelper.PageHelper;
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

@CacheConfig(cacheNames = "customer")
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    private PkBuilder pkBuilder = new PkBuilder();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doCustomer(Customer customer) throws Exception {
        // 传入值为空判断
        if (customer == null) {
            logger.warn("CustomerService.doCustomer传入值为空");
            throw new Exception("传入值为空");
        }
        // 配置customer
        customer.setPkCustomer(pkBuilder.getPk(customer.toString()));
        customer.setTs(format.format(new Date().getTime()));
        // 插入customer
        if (customerMapper.doCustomer(customer) == 0) {
            logger.warn("CustomerService.doCustomer[customerMapper.doCustomer(customer)]有误");
            throw new Exception("用户插入失败");
        }
        logger.debug("CustomerService.doCustomer执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#pk")
    @Transactional(rollbackFor = Exception.class)
    public int deleteCustomer(String pk) throws Exception {
        // 传入值为空判断
        if (pk == null || "".equals(pk)) {
            logger.warn("CustomerService.deleteCustomer传入值为空");
            throw new Exception("传入值为空");
        }
        Customer customer = customerMapper.getCustomerByPk(pk);
        // customer为空判断
        if (customer == null) {
            logger.warn("CustomerService.deleteCustomer.customer为空");
            throw new Exception("数据为空");
        }
        // 数据处理
        customer.setDr(1);
        customer.setTs(format.format(new Date().getTime()));
        // 修改customer
        if (customerMapper.updateCustomer(customer) == 0) {
            logger.warn("CustomerService.deleteCustomer[customerMapper.updateCustomer(customer)]有误");
            throw new Exception("用户删除失败");
        }
        logger.debug("CustomerService.deleteCustomer执行完毕");
        return 1;
    }

    @Override
    @CacheEvict(key = "#customer.pkCustomer")
    @Transactional(rollbackFor = Exception.class)
    public int updateCustomer(Customer customer) throws Exception {
        // 传入值为空判断
        if (customer == null) {
            logger.warn("CustomerService.updateCustomer传入值为空");
            throw new Exception("传入值为空");
        }
        customer.setTs(format.format(new Date().getTime()));
        // 修改customer
        if (customerMapper.updateCustomer(customer) == 0) {
            logger.warn("CustomerService.updateCustomer[customerMapper.updateCustomer(customer)]有误");
            throw new Exception("用户修改失败");
        }
        logger.debug("CustomerService.updateCustomer执行完毕");
        return 1;
    }

    @Override
    public List<Customer> getListCustomer(Customer customer, int nowPage) {
        PageHelper.startPage(nowPage, 10);
        return customerMapper.getListCustomer(customer);
    }

    @Override
    @Cacheable(key = "#pk")
    public Customer getCustomerByPk(String pk) {
        return customerMapper.getCustomerByPk(pk);
    }

    @Override
    public Customer getCustomerByAccount(String account) {
        return customerMapper.getCustomerByAccount(account);
    }
}
