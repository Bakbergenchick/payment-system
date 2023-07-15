package com.silkpay.accountservice.service;


import com.silkpay.accountservice.entity.Account;
import com.silkpay.accountservice.payload.FullResponse;

import java.util.List;

public interface AccountService {
    Account createAccount(Account accountDTO);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    void deleteAccount(Long id);

    FullResponse getCardsByAccountId(Long id);
}
