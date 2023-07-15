package com.silkpay.paymentservice.repository;

import com.silkpay.paymentservice.entity.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
    List<PaymentCard> findAllByAccId(Long id);
}
