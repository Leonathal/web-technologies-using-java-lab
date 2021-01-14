package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.mapper.AccountMapper;
import com.dicualinleon.MusicShop.mapper.AddressMapper;
import com.dicualinleon.MusicShop.service.AccountService;
import com.dicualinleon.MusicShop.service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountController.class)

public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountService accountService;
    @MockBean
    private AddressService addressService;
    @MockBean
    private AccountMapper accountMapper;
    @MockBean
    private AddressMapper addressMapper;
    @MockBean
    private ShoppingCart shoppingCart;

    private Account getAccountTest() {
        return Account.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
    }

    private AccountDto getAccountDtoTest() {
        return AccountDto.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
    }

    @Test
    public void createAccountHappyFlow() throws Exception {
        Account account = Account.builder()
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();

        AccountDto accountDto = AccountDto.builder()
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();

        Account createdAccount = Account.builder()
                .id(1)
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .email(accountDto.getEmail())
                .build();

        AccountDto createdAccountDto = AccountDto.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();

        when(accountMapper.toEntity(accountDto))
                .thenReturn(account);
        when(accountService.create(any()))
                .thenReturn(createdAccount);
        when(accountMapper.toDto(createdAccount))
                .thenReturn(createdAccountDto);

        String jsonObj = objectMapper.writeValueAsString(accountDto);

        mockMvc.perform(post("/account")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(accountDto.getUsername()))
                .andExpect(jsonPath("$.password").value(accountDto.getPassword()))
                .andExpect(jsonPath("$.email").value(accountDto.getEmail()))
                .andReturn();
    }

    @Test
    public void loginHappyFlow() throws Exception {
        Account account = Account.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        AccountDto accountDto = AccountDto.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();

        when(accountService.get(any(), any()))
                .thenReturn(account);
        when(accountMapper.toDto(any()))
                .thenReturn(accountDto);
        doAnswer((i) -> {
            return null;
        }).when(shoppingCart).setCurrentUser(any());

        MvcResult result = mockMvc.perform(
                get("/login")
                .param("username", account.getUsername())
                .param("password", account.getPassword()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(account.getUsername()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(accountDto));
    }
}
