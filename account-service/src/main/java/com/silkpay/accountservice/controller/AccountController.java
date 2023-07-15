package com.silkpay.accountservice.controller;


import com.silkpay.accountservice.entity.Account;
import com.silkpay.accountservice.payload.FullResponse;
import com.silkpay.accountservice.service.AccountService;
import com.silkpay.accountservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody Account accountDTO){
        Account account = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id){
        Account accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @GetMapping("/with-cards/{id}")
    public ResponseEntity<?> getCardsByAccountId(@PathVariable Long id){
        FullResponse cardsByAccountId = accountService.getCardsByAccountId(id);
        return new ResponseEntity<>(cardsByAccountId, HttpStatus.OK);
    }

    @PostMapping("/deposit/{accID}/{cardId}")
    public ResponseEntity<?> depositToCard(
            @PathVariable Long accID,
            @PathVariable Long cardId,
            @RequestParam(name = "balance") Double balance){
        paymentService.depositTo(accID, cardId, balance);
        return new ResponseEntity<>("Successfully replenished!", HttpStatus.OK);
    }

    @PostMapping("/withdraw/{accID}/{cardId}")
    public ResponseEntity<?> withdrawBalance(
            @PathVariable Long accID,
            @PathVariable Long cardId,
            @RequestParam(name = "balance") Double balance){
        paymentService.withDrawBalance(accID, cardId, balance);
        return new ResponseEntity<>("Successfully debited!", HttpStatus.OK);
    }

    @PostMapping("/transfer/{accFrom}/{accTo}/{cardFrom}/{cardTo}")
    public ResponseEntity<?> transferMoney(
            @PathVariable Long accFrom,
            @PathVariable Long accTo,
            @PathVariable Long cardFrom,
            @PathVariable Long cardTo,
            @RequestParam(name = "balance") Double balance){
        paymentService.transferMoney(accFrom, accTo,cardFrom, cardTo, balance);
        return new ResponseEntity<>("Successfully transfered!", HttpStatus.OK);
    }

    @GetMapping("/checkBalance/{accID}/{cardId}")
    public ResponseEntity<?> checkBalance(
            @PathVariable Long accID,
            @PathVariable Long cardId){

        return new ResponseEntity<>(paymentService.checkBalance(accID, cardId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
