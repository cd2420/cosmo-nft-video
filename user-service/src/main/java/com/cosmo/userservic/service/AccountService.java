package com.cosmo.userservic.service;

import com.cosmo.userservic.dto.AccountDto;
import com.cosmo.userservic.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountByAccountId(String accountId);
    Iterable<Account> getAccountByAll();

    AccountDto getAccountDetailsByEmail(String email);

}
