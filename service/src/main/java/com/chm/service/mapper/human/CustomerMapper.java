package com.chm.service.mapper.human;

import com.chm.service.pojo.human.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    /**
     * 增
     * @param customer
     * @return
     */
    public int doCustomer(Customer customer);

    /**
     * 删
     * @param dr
     * @param ts
     * @return
     */
    public int deleteCustomer(@Param("dr") int dr, @Param("ts") String ts);

    /**
     * 改
     * @param customer
     * @return
     */
    public int updateCustomer(Customer customer);

    /**
     * 查（account/name/mail/phone/ts）
     * @param customer
     * @return
     */
    public List<Customer> getListCustomer(Customer customer);

    /**
     * 查（pk）
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
