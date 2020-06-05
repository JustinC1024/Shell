package com.chm.customer.service.impl;

import com.chm.customer.pojo.human.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class CustomerSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", s);
        Customer customer = (Customer)restTemplate.postForObject("http://PROVIDER/visitor_data/login_customer", param, Customer.class);
        String pw;
        if (customer == null) {
            pw = "";
        }else {
            pw = customer.getPassword();
        }
        return new User(s, passwordEncoder.encode(pw), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CUS"));
    }
}
