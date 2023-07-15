package com.silkpay.accountservice.client;

import com.silkpay.accountservice.entity.PaymentCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "payment-service", url = "${application.config.payment-url}")
public interface PaymentClient {
    @GetMapping("/account/{acc_id}")
    List<PaymentCard> findAllCardsByAccount(@PathVariable("acc_id") Long accId);

    @PostMapping
    PaymentCard saveCard(PaymentCard paymentCard);

    @GetMapping("/{id}")
    PaymentCard findCardById(@PathVariable Long id);
}
