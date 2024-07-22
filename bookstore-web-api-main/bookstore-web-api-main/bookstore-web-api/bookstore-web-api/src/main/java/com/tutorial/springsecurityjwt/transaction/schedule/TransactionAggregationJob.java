package com.tutorial.springsecurityjwt.transaction.schedule;

import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import com.tutorial.springsecurityjwt.transaction.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TransactionAggregationJob {

    @Autowired
    private ITransactionRepository repository;

    @Scheduled(cron = "0 0 0 * * ?") // Her gün gece yarısı çalışır
    public void aggregateDailyTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        aggregateTransactions(yesterday, yesterday);
    }

    @Scheduled(cron = "0 0 0 * * MON") // Her hafta başında (Pazartesi) çalışır
    public void aggregateWeeklyTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));

        aggregateTransactions(startOfWeek, endOfWeek);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Her ayın ilk günü çalışır
    public void aggregateMonthlyTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        aggregateTransactions(startOfMonth, endOfMonth);
    }

    private void aggregateTransactions(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = repository.findAllByDateBetween(startDate, endDate);

        Map<String, Double> userTransactionAggregates = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getUser().getUserId(),
                        Collectors.summingDouble(Transaction::getCost)));

        userTransactionAggregates.forEach((userId, totalCost) -> {
            System.out.println("User ID: " + userId + ", Total Cost: " + totalCost);
        });
    }
}
