package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.exception.AccountNotFoundException;
import com.dicualinleon.MusicShop.exception.DuplicateUsernameException;
import com.dicualinleon.MusicShop.repository.AccountDaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountDaoRepository accountDaoRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void createAccountHappyFlow() {
        // arrange
        Account account= Account.builder()
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        Account savedAccount = Account.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        when(accountDaoRepository.getAccount(account.getUsername()))
                .thenReturn(Optional.empty());
        when(accountDaoRepository.create(account))
                .thenReturn(savedAccount);

        // act
        Account result = accountService.create(account);

        // assert
        assertThat(result).isNotNull();

        verify(accountDaoRepository, times(1)).getAccount(account.getUsername());
        verify(accountDaoRepository, times(1)).create(account);

        assertEquals(result.getId(), 1);
    }

    @Test
    public void createAccountDuplicateUsername() throws Exception {
        // arrange
        Account account= Account.builder()
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        Account savedAccount = Account.builder()
                .id(1)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        when(accountDaoRepository.getAccount(account.getUsername()))
                .thenReturn(Optional.of(savedAccount));

        // act
        DuplicateUsernameException exception = assertThrows(
                DuplicateUsernameException.class,
                () -> accountService.create(account),
                "Expected create() to throw DuplicateUsernameException"
        );

        // assert
        assertTrue(exception.getMessage().contains(account.getUsername()));
    }

    @Test
    public void getAccountHappyFlow() {
        // arrange
        long id = 1;
        Account receivedAccount = Account.builder()
                .id(id)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        when(accountDaoRepository.getAccount(id))
                .thenReturn(Optional.of(receivedAccount));

        // act
        Account result = accountService.get(id);

        // assert
        assertEquals(receivedAccount.getId(), result.getId());
        assertEquals(receivedAccount.getUsername(), result.getUsername());
        assertEquals(receivedAccount.getPassword(), result.getPassword());
        assertEquals(receivedAccount.getEmail(), result.getEmail());
    }

    @Test
    public void getAccountAccountNotFoundException() throws Exception {
        // arrange
        long id = 1;
        Account receivedAccount = Account.builder()
                .id(id)
                .username("alin")
                .password("pass")
                .email("alin@email.com")
                .build();
        when(accountDaoRepository.getAccount(id))
                .thenReturn(Optional.empty());

        // act
        AccountNotFoundException exception = assertThrows(
                AccountNotFoundException.class,
                () -> accountService.get(id)
        );

        // asserts
        assertTrue(exception.getMessage().contains(String.valueOf(id)));
    }

    @Test
    public void getAccountUPHappyFlow() {
        // arrange
        String username = "alin";
        String password = "pass";
        Account receivedAccount = Account.builder()
                .id(1)
                .username(username)
                .password(password)
                .email("alin@email.com")
                .build();
        when(accountDaoRepository.getAccount(username, password))
                .thenReturn(Optional.of(receivedAccount));

        // act
        Account result = accountService.get(username, password);

        // assert
        assertEquals(receivedAccount.getId(), result.getId());
        assertEquals(receivedAccount.getUsername(), result.getUsername());
        assertEquals(receivedAccount.getPassword(), result.getPassword());
        assertEquals(receivedAccount.getEmail(), result.getEmail());
    }

    @Test
    public void getAccountUPAccountNotFoundException() throws Exception {
        // arrange
        String username = "alin";
        String password = "pass";
        Account receivedAccount = Account.builder()
                .id(1)
                .username(username)
                .password(password)
                .email("alin@email.com")
                .build();
        when(accountDaoRepository.getAccount(username, password))
                .thenReturn(Optional.empty());

        // act
        AccountNotFoundException exception = assertThrows(
                AccountNotFoundException.class,
                () -> accountService.get(username, password)
        );

        // asserts
        assertTrue(exception.getMessage().contains(username));
    }
}
