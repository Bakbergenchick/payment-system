package com.silkpay.accountservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silkpay.accountservice.client.PaymentClient;
import com.silkpay.accountservice.entity.Account;
import com.silkpay.accountservice.repository.AccountRepository;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        accountRepository.deleteAll();
    }

    @Test
    void createAccountTest() throws Exception {
        Account account = new Account(
                1L,
                "Bakbergen",
                "Atymtay",
                "atymtaevbak@bk.ru"
        );

        ResultActions response = mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(account.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(account.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(account.getEmail())));
    }

    @Test
    void getAllAccountsTest() throws Exception {
        Account account1 = new Account(
                1L,
                "Bakbergen",
                "Atymtay",
                "atymtaev@mail.ru"
        );

        Account account2 = new Account(
                2L,
                "Almas",
                "Jalgasov",
                "jalgasov@gmail.com"
        );

        List<Account> accountList = List.of(account1, account2);

        accountRepository.saveAll(accountList);

        ResultActions response = mockMvc.perform(get("/api/account"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(accountList.size())));

    }

    @Test
    void getAccountByIDTest() throws Exception {

        Account account = new Account();
        account.setFirstName("Almas");
        account.setLastName("Jalgasov");
        account.setEmail("jalgasov@gmail.com");

        accountRepository.save(account);

        ResultActions response = mockMvc.perform(get("/api/account/{id}", account.getId()));

        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        is(account.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(account.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(account.getEmail())));

    }

    @Test
    void deleteAccountTest() throws Exception {
        Account account = new Account();
        account.setFirstName("Almas");
        account.setLastName("Jalgasov");
        account.setEmail("jalgasov@gmail.com");

        accountRepository.save(account);

        ResultActions response = mockMvc.perform(delete("/api/account/{id}", account.getId()));

        response.andDo(print()).
                andExpect(status().isNoContent());

    }
}
