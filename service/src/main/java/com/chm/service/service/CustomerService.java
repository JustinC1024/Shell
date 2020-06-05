package com.chm.service.service;

import com.chm.service.pojo.human.Customer;

import java.util.List;

/**
 * 客户
 */
public interface CustomerService {

    /**
     * 增
     * @param customer
     * @return
     * @throws Exception
     */
    public int doCustomer(Customer customer) throws Exception;

    /**
     * 删
     * @param pk
     * @return
     * @throws Exception
     */
    public int deleteCustomer(String pk) throws Exception;

    /**
     * 改
     * @param customer
     * @return
     * @throws Exception
     */
    public int updateCustomer(Customer customer) throws Exception;

    /**
     * 查（列表）
     * @param customer
     * @param nowPage
     * @return
     */
    public List<Customer> getListCustomer(Customer customer, int nowPage);

    /**
     * 查（单个）
     * @param pk
     * @return
     */
    public Customer getCustomerByPk(String pk);

    /**
     * 查（account）
     * @param account
     * @return
     */
    public Customer getCustomerByAccount(String account);

}
