package com.tutorial.springsecurityjwt.transaction.dto;

import com.tutorial.springsecurityjwt.user.dto.User;
import com.tutorial.springsecurityjwt.user.dto.UserDto;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String transaction_id;
    @NonNull
    @Column(name = "cost")
    private float cost;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="userId")
    private User user;

    @NonNull
    @Column(name = "date")
    private Date date;

    public Transaction() {
    }

    public Transaction(TransactionDto.TransactionRequest transactionRequest) {
        this.transaction_id = transactionRequest.transactionId();
        this.cost = transactionRequest.cost();
       // this.user = new User(transactionRequest.userId());
        this.date = transactionRequest.date();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transaction_id + '\'' +
                ", cost=" + cost +
                ", userId='" + user + '\'' +
                ", date=" + date +
                '}';
    }
}

