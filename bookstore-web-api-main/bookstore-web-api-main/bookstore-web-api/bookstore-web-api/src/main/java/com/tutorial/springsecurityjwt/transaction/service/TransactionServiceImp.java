package com.tutorial.springsecurityjwt.transaction.service;

import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import com.tutorial.springsecurityjwt.transaction.dto.TransactionDto;
import com.tutorial.springsecurityjwt.transaction.repository.ITransactionRepository;
import com.tutorial.springsecurityjwt.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements ITransactionService{
    private final ITransactionRepository repository;

    public TransactionServiceImp(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction getTransactionInformationById(String id) {
        Optional<Transaction> optional = repository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        return optional.get(); // Block until the result is available
    }
    @Override
    public List<Transaction> getTransactionInformations() {
        return repository.findAll(); // Block until all items are collected into a list
    }
    public List<Transaction> getTransactionsByUser(String userId) {
        return repository.findByUserUserId(userId);
    }
    @Override
    public TransactionDto.TransactionRequest saveTransactionInformation(TransactionDto.TransactionRequest transactionInformationDTO) {

        Transaction transaction = repository.save(new Transaction(transactionInformationDTO));
        return new TransactionDto.TransactionRequest (transaction.getTransaction_id(), transaction.getCost(), null, transaction.getDate()); // Block until the result is available
    }

    @Override
    public Transaction updateTransactionInformation(String id, Transaction transactionMono) {
        Transaction transaction = null; // Block until the user is retrieved
        if (transaction != null) {
            transactionMono.setTransaction_id(transaction.getTransaction_id());
            return repository.save(transactionMono); // Block until the result is available
        }
        return null; // Handle case where user is not found
    }
    @Override
    public void deleteTransactionInformation(String id) {
        repository.deleteById(id); // Block until the deletion operation is complete
    }
}
