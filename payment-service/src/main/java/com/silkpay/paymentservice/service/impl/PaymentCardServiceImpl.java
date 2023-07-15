package com.silkpay.paymentservice.service.impl;

import com.silkpay.paymentservice.entity.PaymentCard;
import com.silkpay.paymentservice.repository.PaymentCardRepository;
import com.silkpay.paymentservice.service.PaymentCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentCardServiceImpl implements PaymentCardService {

    private final PaymentCardRepository paymentCardRepository;

    @Override
    public PaymentCard createPaymentCard(PaymentCard paymentCard) {
        return paymentCardRepository.save(paymentCard);
    }

    @Override
    public List<PaymentCard> getAllCards() {
        return paymentCardRepository.findAll();
    }

    @Override
    public PaymentCard getCardById(Long id) {
        return paymentCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("sefef"));
    }

    @Override
    public List<PaymentCard> getPaymentCardsByAccountId(Long accountId) {
        return paymentCardRepository.findAllByAccId(accountId);
    }

    @Override
    public void deleteCard(Long id) {
        paymentCardRepository.deleteById(id);
    }


}
