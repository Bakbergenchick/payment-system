package com.silkpay.accountservice.service.impl;


import com.silkpay.accountservice.client.PaymentClient;
import com.silkpay.accountservice.entity.Account;
import com.silkpay.accountservice.entity.PaymentCard;
import com.silkpay.accountservice.payload.FullResponse;
import com.silkpay.accountservice.repository.AccountRepository;
import com.silkpay.accountservice.service.AccountService;
import com.silkpay.accountservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, PaymentService {

    private final AccountRepository accountRepository;
    private final PaymentClient paymentClient;

    @Override
    public FullResponse getCardsByAccountId(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found"));

        List<PaymentCard> cardsByAccount = paymentClient.findAllCardsByAccount(id);

        FullResponse response = new FullResponse();
        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setPaymentCards(cardsByAccount);

        return response;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void depositTo(Long acc, Long cardId, Double balance) {
        PaymentCard card = paymentClient.findCardById(cardId);

        if (!card.getAccId().equals(acc)){
            throw new IllegalArgumentException("Sefsef");
        }

        card.setBalance(card.getBalance() + balance);
        paymentClient.saveCard(card);
    }

    @Override
    public void withDrawBalance(Long acc, Long cardId, Double balance) {
        PaymentCard card = paymentClient.findCardById(cardId);

        if (!card.getAccId().equals(acc)){
            throw new IllegalArgumentException("Sefsef");
        }

        card.setBalance(card.getBalance() - balance);
        paymentClient.saveCard(card);
    }

    @Override
    public void transferMoney(Long accFrom, Long accTo, Long cardFrom, Long cardTo, Double balance) {
        PaymentCard paymentCardFrom = paymentClient.findCardById(cardFrom);

        PaymentCard paymentCardTo = paymentClient.findCardById(cardTo);

        if (!paymentCardFrom.getAccId().equals(accFrom)){
            throw new IllegalArgumentException("Sefsef");
        }

        if (!paymentCardTo.getAccId().equals(accTo)){
            throw new IllegalArgumentException("Sefsef");
        }

        if (accFrom.equals(accTo)){
            if (cardFrom.equals(cardTo))
                throw new IllegalArgumentException("Within two identical accounts and cards don't support transfer");
        }

        if (paymentCardFrom.getBalance().compareTo(balance) < 0){
            throw new IllegalArgumentException("In card don't have enough money to transfer");
        }

        paymentCardFrom.setBalance(paymentCardFrom.getBalance() - balance);
        paymentCardTo.setBalance(paymentCardTo.getBalance() + balance);
        paymentClient.saveCard(paymentCardFrom);
        paymentClient.saveCard(paymentCardTo);
    }

    @Override
    public PaymentCard checkBalance(Long acc, Long cardId) {
        PaymentCard card = paymentClient.findCardById(cardId);

        if (!card.getAccId().equals(acc)){
            throw new IllegalArgumentException("Sefsef");
        }

        return card;
    }
}
