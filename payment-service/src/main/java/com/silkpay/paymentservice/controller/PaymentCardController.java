package com.silkpay.paymentservice.controller;


import com.silkpay.paymentservice.entity.PaymentCard;
import com.silkpay.paymentservice.service.PaymentCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentCardController {
    private final PaymentCardService cardService;

    // Payment Card endpoints
    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody PaymentCard card){
        PaymentCard paymentCard = cardService.createPaymentCard(card);
        return new ResponseEntity<>(paymentCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getCards(){
        return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id){
        return new ResponseEntity<>(cardService.getCardById(id), HttpStatus.OK);
    }

    @GetMapping("/account/{acc_id}")
    public ResponseEntity<?> getCardByAccountId(@PathVariable Long acc_id){
        return new ResponseEntity<>(cardService.getPaymentCardsByAccountId(acc_id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id){
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
