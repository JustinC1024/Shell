package com.chm.service.pojo.human;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class Customer implements Serializable {

    private String pkCustomer;
    @NotBlank(message = "customer.account")
    @Length(min = 11, max = 11, message = "customer.account")
    private String account;
    private String password;
    private byte[] portrait;
    private String name;
    private String identity;
    private String city;
    private int gender;
    private String birth;
    @Email(message = "customer.mail")
    private String mail;
    private String phone;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
