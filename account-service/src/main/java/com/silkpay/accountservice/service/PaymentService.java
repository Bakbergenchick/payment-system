package com.silkpay.accountservice.service;


import com.silkpay.accountservice.entity.PaymentCard;

public interface PaymentService {
    void depositTo(Long acc, Long cardId, Double balance);
    void withDrawBalance(Long acc,Long cardId, Double balance);
    void transferMoney(Long accFrom, Long accTo, Long cardFrom, Long cardTo, Double balance);
    PaymentCard checkBalance(Long acc, Long cardId);
}
