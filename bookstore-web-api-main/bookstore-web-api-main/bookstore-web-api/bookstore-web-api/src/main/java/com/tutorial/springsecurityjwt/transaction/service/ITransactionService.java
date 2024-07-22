package com.tutorial.springsecurityjwt.transaction.service;

import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import com.tutorial.springsecurityjwt.transaction.dto.TransactionDto;


import java.util.List;

public interface ITransactionService {
    Transaction getTransactionInformationById(String id);
    List<Transaction> getTransactionInformations();

    TransactionDto.TransactionRequest saveTransactionInformation(TransactionDto.TransactionRequest transactionRequestDTO);

    Transaction updateTransactionInformation(String id, Transaction transactionInformationDTO);

    void deleteTransactionInformation(String id);
}
