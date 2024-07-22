package com.tutorial.springsecurityjwt.transaction.controller;
import com.tutorial.springsecurityjwt.exceptions.TransactionNotFoundException;
import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import com.tutorial.springsecurityjwt.transaction.dto.TransactionDto;
import com.tutorial.springsecurityjwt.transaction.service.TransactionServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionRestController {
    @Autowired
    TransactionServiceImp transactionService;

    @PostMapping()
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto.TransactionRequest newTransaction) throws IllegalAccessException{
        TransactionDto.TransactionRequest transaction = transactionService.saveTransactionInformation(newTransaction);

        TransactionDto.Response response = new TransactionDto.Response("Transaction created successfully", transaction);
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<List<TransactionDto.Response>> getAllTransactions() {
        List<TransactionDto.Response> transactions = transactionService.getTransactionInformations().stream()
                .map(transaction -> {
                    TransactionDto.TransactionRequest request = new TransactionDto.TransactionRequest(transaction.getTransaction_id(), transaction.getCost(), transaction.getUser().getUserId(),transaction.getDate());
                    return new TransactionDto.Response("Transactions listed successfully", request);
                })
                .toList();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/{totalCost}")//Son dk yazdım
    @ApiOperation(value = "Get sum of transactions for a user", response = ResponseEntity.class)
    public Float getSumOfTransactionsForOneUser(@RequestParam String userId) {
        List<Transaction> transactions = transactionService.getTransactionInformations();
        double totalCost = transactions.stream()
                .filter(transaction -> transaction.getUser().getUserId().equals(userId))
                .mapToDouble(Transaction::getCost)
                .sum();
        return (float)totalCost;
    }
    @GetMapping("/{transactionId}")
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<?> getOneTransaction(@PathVariable String transactionId) {
        Transaction transaction = transactionService.getTransactionInformationById(transactionId);
        if(transaction == null) {
            throw new TransactionNotFoundException();
        }
        return ResponseEntity.ok(transaction);
    }
    @PutMapping("/{transactionId}")
    @ApiOperation(value = "transaction update", response = ResponseEntity.class)
    public ResponseEntity<Void> updateOneTransaction(@PathVariable String transactionId, @RequestBody Transaction newTransaction) {
        Transaction transaction = transactionService.updateTransactionInformation(transactionId, newTransaction);
        if(transaction != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/{transactionId}")
    @ApiOperation(value = "Delete a transaction by ID")

    public void deleteOneTransaction(@PathVariable String transactionId) {
        transactionService.deleteTransactionInformation(transactionId);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<List<TransactionDto.Response>> getTransactionsByUser(@PathVariable String userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUser(userId);
        List<TransactionDto.Response> responseList = transactions.stream()
                .map(transaction -> {
                    TransactionDto.TransactionRequest request = new TransactionDto.TransactionRequest(transaction.getTransaction_id(), transaction.getCost(), transaction.getUser().getUserId(), transaction.getDate());
                    return new TransactionDto.Response("Transactions listed successfully", request);
                })
                .toList();
        return ResponseEntity.ok(responseList);
    }

}
