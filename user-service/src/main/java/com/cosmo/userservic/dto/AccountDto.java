package com.cosmo.userservic.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String accountId;

    private Date createdAt;

}
