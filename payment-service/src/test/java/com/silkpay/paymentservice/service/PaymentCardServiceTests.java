package com.silkpay.paymentservice.service;


import com.silkpay.paymentservice.entity.PaymentCard;
import com.silkpay.paymentservice.repository.PaymentCardRepository;
import com.silkpay.paymentservice.service.impl.PaymentCardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PaymentCardServiceTests {
    @Mock
    private PaymentCardRepository paymentCardRepository;

    @InjectMocks
    private PaymentCardServiceImpl paymentCardService;

    public PaymentCard paymentCard;

    @BeforeEach
    public void setUp() {
        paymentCard = new PaymentCard(
                1L,
                1345567556754565L,
                2000D, 123L,
                LocalDate.parse("2012-10-08"),
                1L);
    }

    @Test
    void testSaveMethod() {
        given(paymentCardRepository.save(paymentCard))
                .willReturn(paymentCard);

        PaymentCard paymentCard1 = paymentCardService.createPaymentCard(paymentCard);

        Assertions.assertEquals(paymentCard1, paymentCard);
    }

    @Test
    void testSaveAllCards(){
        PaymentCard paymentCard1 = new PaymentCard(2L,
                1345567556754565L,
                2000D, 123L,
                LocalDate.parse("2012-10-08"),
                1L);

        given(paymentCardRepository.findAll())
                .willReturn(List.of(paymentCard, paymentCard1));

        List<PaymentCard> cards = paymentCardService.getAllCards();

        Assertions.assertNotNull(cards.size());
        Assertions.assertEquals(cards.size(), 2);
    }

    @Test
    void testGetCardByID(){
        given(paymentCardRepository.findById(1L))
                .willReturn(Optional.of(paymentCard));

        PaymentCard cardById = paymentCardService.getCardById(paymentCard.getCard_id());

        Assertions.assertNotNull(cardById);
    }

    @Test
    void testDeleteById(){
        willDoNothing().given(paymentCardRepository).deleteById(1L);

        paymentCardService.deleteCard(1L);

        verify(paymentCardRepository, times(1)).deleteById(1L);

    }

}
