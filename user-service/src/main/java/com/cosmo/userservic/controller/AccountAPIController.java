package com.cosmo.userservic.controller;

import com.cosmo.userservic.dto.AccountDto;
import com.cosmo.userservic.entity.Account;
import com.cosmo.userservic.service.AccountService;
import com.cosmo.userservic.vo.RequestAccount;
import com.cosmo.userservic.vo.ResponseAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class AccountAPIController {

    private final AccountService accountService;

    @GetMapping("/check-health")
    public String checkHealth( ) {

        return "hello world";

    }

    @PostMapping("/account")
    public ResponseEntity createAccount( @RequestBody RequestAccount requestAccount ) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AccountDto accountDto = mapper.map(requestAccount, AccountDto.class);
        ResponseAccount responseAccount = mapper.map(accountService.createAccount(accountDto), ResponseAccount.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccount);

    }

    @GetMapping("/accounts")
    public ResponseEntity<List<ResponseAccount>> getAccounts() {
        Iterable<Account> accounts = accountService.getAccountByAll();
        List<ResponseAccount> result = new ArrayList<>();
        accounts.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseAccount.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<ResponseAccount> getAccount(@PathVariable("accountId") String accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountByAccountId(accountId));
    }

}
