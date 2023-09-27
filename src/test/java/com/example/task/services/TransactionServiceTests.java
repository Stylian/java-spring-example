package com.example.task.services;

import com.example.task.dtos.TransactionRequest;
import com.example.task.entities.Account;
import com.example.task.entities.Transaction;
import com.example.task.exceptions.AccountNotExistsException;
import com.example.task.exceptions.IdenticalAccountsException;
import com.example.task.exceptions.InsufficientBalanceException;
import com.example.task.repositories.AccountRepository;
import com.example.task.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTests {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @InjectMocks
    TransactionServiceImpl transactionService;

    public static final long SOURCE_ACCOUNT_ID = 1L;
    public static final double SOURCE_ACCOUNT_BALANCE = 500;
    public static final long TARGET_ACCOUNT_ID = 2L;
    public static final double TARGET_ACCOUNT_BALANCE = 2000;
    public static final String CURRENCY = "EUR";
    public static final String OTHER_CURRENCY = "USD";

    @BeforeAll
    public void setup() {

        Mockito.when(currencyConversionService.convert(Mockito.anyDouble(), Mockito.anyString(), Mockito.anyString()))
                .thenAnswer(args -> {
                    if(CURRENCY.equals(args.getArguments()[1]) && OTHER_CURRENCY.equals(args.getArguments()[2])) {
                        return 1.25 * (double) args.getArguments()[0];
                    }else if(OTHER_CURRENCY.equals(args.getArguments()[1]) && OTHER_CURRENCY.equals(args.getArguments()[2])) {
                        return args.getArguments()[0];
                    }else if(OTHER_CURRENCY.equals(args.getArguments()[1]) && CURRENCY.equals(args.getArguments()[2])) {
                        return 0.8 * (double) args.getArguments()[0];
                    }else if(CURRENCY.equals(args.getArguments()[1]) && CURRENCY.equals(args.getArguments()[2])) {
                        return args.getArguments()[0];
                    }else {
                        return 0;
                    }
                });
    }

    @Test
    public void testExecuteTransfer() throws IdenticalAccountsException, InsufficientBalanceException, AccountNotExistsException {
        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);
        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);

        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);

        TransactionRequest transactionRequest = new TransactionRequest(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Transaction result = transactionService.executeTransfer(transactionRequest);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testExecuteTransferWithDifferentCurrencies() throws IdenticalAccountsException, InsufficientBalanceException, AccountNotExistsException {
        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, OTHER_CURRENCY);
        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);

        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);

        TransactionRequest transactionRequest = new TransactionRequest(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Transaction result = transactionService.executeTransfer(transactionRequest);
        Assertions.assertEquals(187.5, result.getSourceAccount().getBalance());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testIDenticalAccountsError() {
        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);
        Account targetAccount = new Account(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);

        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);

        TransactionRequest transactionRequest = new TransactionRequest(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Assertions.assertThrows(IdenticalAccountsException.class, () -> {
            transactionService.executeTransfer(transactionRequest);
        });
    }

    @Test
    public void testAccountDoesNotExistError() {
        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);

        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(null);

        TransactionRequest transactionRequest = new TransactionRequest(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 250, CURRENCY);
        Transaction transaction = new Transaction(sourceAccount, null, 250, CURRENCY);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Assertions.assertThrows(AccountNotExistsException.class, () -> {
            transactionService.executeTransfer(transactionRequest);
        });
    }

    @Test
    public void testInsufficientBalanceError() {
        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, CURRENCY);
        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);

        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);

        TransactionRequest transactionRequest = new TransactionRequest(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 700, CURRENCY);
        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.executeTransfer(transactionRequest);
        });
    }

    @Test
    public void testInsufficientBalanceWithDifferntCurrenciesError() {
        Account sourceAccount = new Account(SOURCE_ACCOUNT_ID,SOURCE_ACCOUNT_BALANCE, OTHER_CURRENCY);
        Account targetAccount = new Account(TARGET_ACCOUNT_ID, TARGET_ACCOUNT_BALANCE, CURRENCY);

        Mockito.when(accountRepository.findById(SOURCE_ACCOUNT_ID)).thenReturn(sourceAccount);
        Mockito.when(accountRepository.findById(TARGET_ACCOUNT_ID)).thenReturn(targetAccount);

        TransactionRequest transactionRequest = new TransactionRequest(SOURCE_ACCOUNT_ID, TARGET_ACCOUNT_ID, 450, CURRENCY);
        Transaction transaction = new Transaction(sourceAccount, targetAccount, 250, CURRENCY);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.executeTransfer(transactionRequest);
        });
    }
}
