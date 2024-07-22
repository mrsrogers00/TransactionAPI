package com.tutorial.springsecurityjwt.transaction.repository;

import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import com.tutorial.springsecurityjwt.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByUserUserId(String userId);

    List<Transaction> findAll();

    Transaction save(Transaction transaction);
    List<Transaction> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    void deleteById(String id);
}
