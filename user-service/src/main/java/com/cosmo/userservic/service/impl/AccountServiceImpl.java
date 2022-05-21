package com.cosmo.userservic.service.impl;

import com.cosmo.userservic.client.VideoServiceClient;
import com.cosmo.userservic.dto.AccountDto;
import com.cosmo.userservic.entity.Account;
import com.cosmo.userservic.jpa.AccountRepository;
import com.cosmo.userservic.service.AccountService;
import com.cosmo.userservic.vo.ResponseAccount;
import com.cosmo.userservic.vo.ResponseVideo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VideoServiceClient videoServiceClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(s));

        return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {
        accountDto.setAccountId(UUID.randomUUID().toString());

        ModelMapper mapper = getModelMapper();
        Account account = mapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return mapper.map(account, AccountDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseAccount getAccountByAccountId(String accountId) {

        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException(accountId));
        ModelMapper mapper = getModelMapper();
        ResponseAccount responseAccount = mapper.map(account, ResponseAccount.class);

        List<ResponseVideo> videos = videoServiceClient.getVideos(responseAccount.getAccountId());
        responseAccount.setVideos(videos);

        return responseAccount;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Account> getAccountByAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountDetailsByEmail(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        ModelMapper mapper = getModelMapper();
        return mapper.map(account, AccountDto.class);
    }

    private ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

}
