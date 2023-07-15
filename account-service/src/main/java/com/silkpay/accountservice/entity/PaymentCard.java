package com.silkpay.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCard {

    private Long card_id;
    private Long cardNo;
    private Double balance;
    private Long cvvCode;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate expirationDate;
    private Long accId;
}
