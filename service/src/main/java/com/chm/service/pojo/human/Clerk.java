package com.chm.service.pojo.human;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class Clerk implements Serializable {

    private String pkClerk;
    private String name;
    private String img;
    private String introduction;
    @Length(min = 11, max = 11, message = "clerk.phone")
    private String phone;
    @NotBlank(message = "clerk.account")
    private String account;
    private String password;
    private Jurisdiction pkJurisdiction;
    private String parent;
    private Integer dr;
    private String ts;
    private String spare1;
    private String spare2;
    private String spare3;

}
