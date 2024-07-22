package com.tutorial.springsecurityjwt.transaction;
import com.tutorial.springsecurityjwt.exceptions.TransactionNotFoundException;
import com.tutorial.springsecurityjwt.transaction.controller.TransactionRestController;
import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import com.tutorial.springsecurityjwt.transaction.dto.TransactionDto;
import com.tutorial.springsecurityjwt.transaction.service.TransactionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TransactionRestControllerTest {

    @InjectMocks
    private TransactionRestController transactionRestController;

    @Mock
    private TransactionServiceImp transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransaction() throws IllegalAccessException {
        Date date = new Date();

        // TransactionRequest nesnesi oluşturun

        TransactionDto.TransactionRequest request = new TransactionDto.TransactionRequest(
                "txn12345",    // transactionId
                150.75f,       // cost
                "user67890",   // userId
                date           // date
        );
        TransactionDto.TransactionRequest savedTransaction = new TransactionDto.TransactionRequest(
                "txn12345",    // transactionId
                150.75f,       // cost
                "user67890",   // userId
                date           // date
        );;
        when(transactionService.saveTransactionInformation(request)).thenReturn(savedTransaction);

        ResponseEntity<?> responseEntity = transactionRestController.createTransaction(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(transactionService, times(1)).saveTransactionInformation(request);
    }

    //@Test
    //public void testGetAllTransactions() {
    //    List<Transaction> transactions = new ArrayList<>();
    //    Transaction transaction = new Transaction();
    //    transactions.add(transaction);
//
    //    when(transactionService.getTransactionInformations()).thenReturn(transactions);
//
    //    ResponseEntity<List<TransactionDto.Response>> responseEntity = transactionRestController.getAllTransactions();
//
    //    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    //    verify(transactionService, times(1)).getTransactionInformations();
    //}

    @Test
    public void testGetSumOfTransactionsForOneUser() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setCost((float) 100.0F);
        when(transactionService.getTransactionInformations()).thenReturn(transactions);

        Float totalCost = transactionRestController.getSumOfTransactionsForOneUser("userId");

        assertEquals(0.0f, totalCost);
        verify(transactionService, times(1)).getTransactionInformations();
    }

    @Test
    public void testGetOneTransaction() {
        Transaction transaction = new Transaction();
        when(transactionService.getTransactionInformationById(anyString())).thenReturn(transaction);

        ResponseEntity<?> responseEntity = transactionRestController.getOneTransaction("transactionId");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(transactionService, times(1)).getTransactionInformationById("transactionId");
    }

    @Test
    public void testGetOneTransaction_NotFound() {
        when(transactionService.getTransactionInformationById(anyString())).thenReturn(null);

        assertThrows(TransactionNotFoundException.class, () -> {
            transactionRestController.getOneTransaction("transactionId");
        });

        verify(transactionService, times(1)).getTransactionInformationById("transactionId");
    }

    @Test
    public void testUpdateOneTransaction() {
        Transaction transaction = new Transaction();
        when(transactionService.updateTransactionInformation(anyString(), any(Transaction.class))).thenReturn(transaction);

        ResponseEntity<Void> responseEntity = transactionRestController.updateOneTransaction("transactionId", transaction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(transactionService, times(1)).updateTransactionInformation("transactionId", transaction);
    }

    @Test
    public void testDeleteOneTransaction() {
        doNothing().when(transactionService).deleteTransactionInformation(anyString());

        transactionRestController.deleteOneTransaction("transactionId");

        verify(transactionService, times(1)).deleteTransactionInformation("transactionId");
    }

    //@Test
    //public void testGetTransactionsByUser() {
    //    List<Transaction> transactions = new ArrayList<>();
    //    Transaction transaction = new Transaction();
    //    transactions.add(transaction);
    //    when(transactionService.getTransactionsByUser(anyString())).thenReturn(transactions);
//
    //    ResponseEntity<List<TransactionDto.Response>> responseEntity = transactionRestController.getTransactionsByUser("userId");
//
    //    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    //    verify(transactionService, times(1)).getTransactionsByUser("userId");
    //}
}
