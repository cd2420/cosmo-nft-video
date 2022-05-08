package com.cosmo.userservic.jpa;

import com.cosmo.userservic.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByAccountId(String accountId);
}
