package com.silkpay.accountservice.payload;

import com.silkpay.accountservice.entity.PaymentCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<PaymentCard> paymentCards;
}
