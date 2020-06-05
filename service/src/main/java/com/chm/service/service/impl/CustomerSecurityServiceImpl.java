package com.chm.service.service.impl;

import com.chm.service.pojo.human.Clerk;
import com.chm.service.pojo.human.Customer;
import com.chm.service.service.ClerkService;
import com.chm.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
public class CustomerSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Clerk clerk = clerkService.getClerkByAccount(s);
        String pw = clerk.getPassword();
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_NOM"));
        authorities.add(new SimpleGrantedAuthority(clerk.getPkJurisdiction().getSpare1()));
        return new User(s, passwordEncoder.encode(pw), authorities);
    }

    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = new Customer();
        customer.setAccount(s);
        List<Customer> customerList = customerService.getListCustomer(customer, 1);
        if (customerList.size() == 0) {
            return null;
        }
        String pw = customerList.get(0).getPassword();
        return new User(s, passwordEncoder.encode(pw), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CUS"));
    }*/
}
