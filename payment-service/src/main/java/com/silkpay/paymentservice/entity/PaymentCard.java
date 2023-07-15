package com.silkpay.paymentservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false)
    private Long card_id;
    private Long cardNo;
    private Double balance;
    private Long cvvCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    private Long accId;
}
