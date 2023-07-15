package com.silkpay.paymentservice.service;



import com.silkpay.paymentservice.entity.PaymentCard;


import java.util.List;

public interface PaymentCardService {
    PaymentCard createPaymentCard(PaymentCard paymentCard);
    List<PaymentCard> getAllCards();

    PaymentCard getCardById(Long id);
    List<PaymentCard> getPaymentCardsByAccountId(Long accountId);

    void deleteCard(Long id);
}
